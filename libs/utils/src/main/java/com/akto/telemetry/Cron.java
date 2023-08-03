package com.akto.telemetry;

import com.akto.dao.*;
import com.akto.dao.context.Context;
import com.akto.dto.AccountSettings;
import com.akto.dto.Log;
import com.akto.log.LoggerMaker;
import com.akto.util.AccountUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import okhttp3.*;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Cron {

    private static final LoggerMaker loggerMaker = new LoggerMaker(Cron.class);

    private static final String accountName = AccountUtils.getAccountName();

    private static final String telemetryUrl = "http://prod-usage-alb-1371096032.us-east-1.elb.amazonaws.com/ingest";

    public void init(){
        try {
            Context.accountId.set(1_000_000);
            loggerMaker.infoAndAddToDb("Starting telemetry cron", LoggerMaker.LogDb.DASHBOARD);
            AccountSettings accountSettings = AccountSettingsDao.instance.findOne(AccountSettingsDao.generateFilter());
            if (accountSettings == null) {
                loggerMaker.infoAndAddToDb("AccountSettings is missing, skipping telemetry cron", LoggerMaker.LogDb.DASHBOARD);
                return;
            }
            if (!accountSettings.isEnableTelemetry()) {
                loggerMaker.infoAndAddToDb("Telemetry is disabled, skipping telemetry cron", LoggerMaker.LogDb.DASHBOARD);
                return;
            }
            loggerMaker.infoAndAddToDb("Running telemetry cron", LoggerMaker.LogDb.DASHBOARD);
            Map<String, Integer> telemetryUpdateSentTsMap = accountSettings.getTelemetryUpdateSentTsMap();
            if(telemetryUpdateSentTsMap == null){
                telemetryUpdateSentTsMap = new HashMap<>();
            }
            int now = Context.now();
            //Fetch logs from lastRunTs to now
            fetchAndSendLogs(telemetryUpdateSentTsMap, now, LoggerMaker.LogDb.TESTING);
            fetchAndSendLogs(telemetryUpdateSentTsMap, now, LoggerMaker.LogDb.DASHBOARD);
            fetchAndSendLogs(telemetryUpdateSentTsMap, now, LoggerMaker.LogDb.RUNTIME);
            fetchAndSendLogs(telemetryUpdateSentTsMap, now, LoggerMaker.LogDb.ANALYSER);
        }catch (Exception e){
            loggerMaker.errorAndAddToDb("Telemetry cron failed due to:" + e.getMessage(), LoggerMaker.LogDb.DASHBOARD);
        }

    }

    private static AccountsContextDao<Log> getInstance(LoggerMaker.LogDb dbName){
        switch (dbName){
            case DASHBOARD:
                return DashboardLogsDao.instance;
            case RUNTIME:
                return RuntimeLogsDao.instance;
            case ANALYSER:
                return AnalyserLogsDao.instance;
            default:
                return LogsDao.instance;
        }
    }

    public static void fetchAndSendLogs(Map<String, Integer> telemetryUpdateSentTsMaps, int toTs, LoggerMaker.LogDb dbName){
        int skip = 0;
        int limit = 100;
        int fromTs = telemetryUpdateSentTsMaps.getOrDefault(dbName.name(), 0);
        Bson filter = Filters.and(
                Filters.gte(Log.TIMESTAMP, fromTs),
                Filters.lte(Log.TIMESTAMP, toTs)
        );
        AccountsContextDao<Log> instance = getInstance(dbName);
        List<Log> logs;
        loggerMaker.infoAndAddToDb("Starting to fetchAndSendLogs for " + dbName.name(), LoggerMaker.LogDb.DASHBOARD);
        do{
            //Send logs to telemetry server
            logs = instance.findAll(filter, skip, limit, Sorts.ascending(Log.TIMESTAMP), null);
            skip += limit;
            //send logs to telemetry server
            int lastLogTs = sendLogs(logs, dbName);
            if(lastLogTs <=0) {
                break;
            }
            AccountSettingsDao.instance.updateLastTelemetryUpdateSentTs(dbName.name(), lastLogTs);
            loggerMaker.infoAndAddToDb("Successfully sent a batch of " + logs.size() + " logs for " + dbName.name(), LoggerMaker.LogDb.DASHBOARD);
        }while (logs.size() == limit);
    }

    private static final OkHttpClient client = new OkHttpClient().newBuilder()
                .writeTimeout(1,TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .callTimeout(1, TimeUnit.SECONDS)
                .build();

    private static int sendLogs(List<Log> logs, LoggerMaker.LogDb dbName) {
        if(logs.isEmpty()){
            loggerMaker.infoAndAddToDb("Logs list is empty for db:" + dbName + " skipping", LoggerMaker.LogDb.DASHBOARD);
            return 0;
        }
        BasicDBList telemetryLogs = logs.stream()
                .map(log ->
                        new BasicDBObject("_id", log.getId())
                                .append("log", log.getLog())
                                .append("key", log.getKey())
                                .append("timestamp", log.getTimestamp())
                                )
                .collect(Collectors.toCollection(BasicDBList::new));

        BasicDBObject data = new BasicDBObject("service", dbName.name())
                .append("logs", telemetryLogs)
                .append("accountName", accountName)
                .append("type", "TELEMETRY");

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(new BasicDBObject("data", data).toJson(), mediaType);
        Request request = new Request.Builder()
                .url(telemetryUrl)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response =  client.newCall(request).execute();
        } catch (IOException e) {
            loggerMaker.errorAndAddToDb("Error while executing request " + request.url() + ": " + e.getMessage(), LoggerMaker.LogDb.DASHBOARD);
            return -1;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return response.isSuccessful() ? logs.get(logs.size()-1).getTimestamp(): -1;
    }

}
