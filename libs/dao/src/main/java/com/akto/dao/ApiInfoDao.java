package com.akto.dao;

import com.akto.dao.context.Context;
import com.akto.dto.ApiCollectionUsers;
import com.akto.dto.ApiInfo;
import com.akto.dto.ApiInfo.ApiAccessType;
import com.akto.dto.ApiInfo.ApiInfoKey;
import com.akto.dto.rbac.UsersCollectionsList;
import com.akto.dto.testing.TestingEndpoints;
import com.akto.dto.ApiStats;
import com.akto.util.Constants;
import com.akto.util.Pair;
import com.mongodb.BasicDBObject;
import com.akto.dto.type.SingleTypeInfo;
import com.akto.dto.type.URLMethods.Method;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UnwindOptions;
import com.mongodb.client.model.Updates;

import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApiInfoDao extends AccountsContextDaoWithRbac<ApiInfo>{

    public static final ApiInfoDao instance = new ApiInfoDao();

    public static final String ID = "_id.";
    public static final int AKTO_DISCOVERED_APIS_COLLECTION_ID = 1333333333;

    public void createIndicesIfAbsent() {

        boolean exists = false;
        for (String col: clients[0].getDatabase(Context.accountId.get()+"").listCollectionNames()){
            if (getCollName().equalsIgnoreCase(col)){
                exists = true;
                break;
            }
        };

        if (!exists) {
            clients[0].getDatabase(Context.accountId.get()+"").createCollection(getCollName());
        }

        String[] fieldNames = {"_id." + ApiInfo.ApiInfoKey.API_COLLECTION_ID};
        MCollection.createIndexIfAbsent(getDBName(), getCollName(), fieldNames, true);

        fieldNames = new String[]{"_id." + ApiInfo.ApiInfoKey.URL};
        MCollection.createIndexIfAbsent(getDBName(), getCollName(), fieldNames, true);
        
        fieldNames = new String[]{"_id." + ApiInfo.ApiInfoKey.API_COLLECTION_ID, "_id." + ApiInfo.ApiInfoKey.URL};
        MCollection.createIndexIfAbsent(getDBName(), getCollName(), fieldNames, true);

        fieldNames = new String[]{ApiInfo.ID_API_COLLECTION_ID, ApiInfo.LAST_SEEN};
        MCollection.createIndexIfAbsent(getDBName(), getCollName(), fieldNames, false);

        fieldNames = new String[]{ApiInfo.LAST_TESTED};
        MCollection.createIndexIfAbsent(getDBName(), getCollName(), fieldNames, false);

        fieldNames = new String[]{ApiInfo.LAST_CALCULATED_TIME};
        MCollection.createIndexIfAbsent(getDBName(), getCollName(), fieldNames, false);

        MCollection.createIndexIfAbsent(getDBName(), getCollName(),
                new String[] { SingleTypeInfo._COLLECTION_IDS, ApiInfo.ID_URL }, true);

        MCollection.createIndexIfAbsent(getDBName(), getCollName(),
                new String[] {ApiInfo.SEVERITY_SCORE }, false);
                
        MCollection.createIndexIfAbsent(getDBName(), getCollName(),
                new String[] {ApiInfo.RISK_SCORE }, false);

        MCollection.createIndexIfAbsent(getDBName(), getCollName(),
                new String[] { ApiInfo.RISK_SCORE, ApiInfo.ID_API_COLLECTION_ID }, false);

        MCollection.createIndexIfAbsent(getDBName(), getCollName(),
            new String[] {ApiInfo.DISCOVERED_TIMESTAMP }, false);
    }
    

    public void updateLastTestedField(ApiInfoKey apiInfoKey){
        instance.getMCollection().updateOne(
            getFilter(apiInfoKey), 
            Updates.set(ApiInfo.LAST_TESTED, Context.now())
        );
    }

    public Map<Integer,Integer> getCoverageCount(){
        Map<Integer,Integer> result = new HashMap<>();
        List<Bson> pipeline = new ArrayList<>();
        int oneMonthAgo = Context.now() - Constants.ONE_MONTH_TIMESTAMP ;
        pipeline.add(Aggregates.match(Filters.gte("lastTested", oneMonthAgo)));

        try {
            List<Integer> collectionIds = UsersCollectionsList.getCollectionsIdForUser(Context.userId.get(), Context.accountId.get());
            if(collectionIds != null) {
                pipeline.add(Aggregates.match(Filters.in(SingleTypeInfo._COLLECTION_IDS, collectionIds)));
            }
        } catch(Exception e){
        }

        UnwindOptions unwindOptions = new UnwindOptions();
        unwindOptions.preserveNullAndEmptyArrays(false);  
        pipeline.add(Aggregates.unwind("$collectionIds", unwindOptions));

        BasicDBObject groupedId2 = new BasicDBObject("apiCollectionId", "$collectionIds");
        pipeline.add(Aggregates.group(groupedId2, Accumulators.sum("count",1)));
        pipeline.add(Aggregates.project(
            Projections.fields(
                Projections.include("count"),
                Projections.computed("apiCollectionId", "$_id.apiCollectionId")
            )
        ));

        MongoCursor<BasicDBObject> collectionsCursor = ApiInfoDao.instance.getMCollection().aggregate(pipeline, BasicDBObject.class).cursor();
        while(collectionsCursor.hasNext()){
            try {
                BasicDBObject basicDBObject = collectionsCursor.next();
                result.put(basicDBObject.getInt("apiCollectionId"), basicDBObject.getInt("count"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Map<Integer,Integer> getLastTrafficSeen(){
        Map<Integer,Integer> result = new HashMap<>();
        List<Bson> pipeline = new ArrayList<>();

        try {
            List<Integer> collectionIds = UsersCollectionsList.getCollectionsIdForUser(Context.userId.get(), Context.accountId.get());
            if(collectionIds != null) {
                pipeline.add(Aggregates.match(Filters.in(SingleTypeInfo._COLLECTION_IDS, collectionIds)));
            }
        } catch(Exception e){
        }

        UnwindOptions unwindOptions = new UnwindOptions();
        unwindOptions.preserveNullAndEmptyArrays(false);  
        pipeline.add(Aggregates.unwind("$collectionIds", unwindOptions));

        BasicDBObject groupedId = new BasicDBObject("apiCollectionId", "$collectionIds");
        pipeline.add(Aggregates.sort(Sorts.orderBy(Sorts.descending(ApiInfo.ID_API_COLLECTION_ID), Sorts.descending(ApiInfo.LAST_SEEN))));
        pipeline.add(Aggregates.group(groupedId, Accumulators.first(ApiInfo.LAST_SEEN, "$lastSeen")));
        
        MongoCursor<ApiInfo> cursor = ApiInfoDao.instance.getMCollection().aggregate(pipeline, ApiInfo.class).cursor();
        while(cursor.hasNext()){
            try {
               ApiInfo apiInfo = cursor.next();
               result.put(apiInfo.getId().getApiCollectionId(), apiInfo.getLastSeen());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Float getRiskScore(ApiInfo apiInfo, boolean isSensitive, float riskScoreFromSeverityScore){
        float riskScore = 0;
        if(apiInfo != null){
            if(Context.now() - apiInfo.getLastSeen() <= Constants.ONE_MONTH_TIMESTAMP){
                riskScore += 1;
            }
            if(apiInfo.getApiAccessTypes().contains(ApiAccessType.PUBLIC)){
                riskScore += 1;
            }
        }
        if(isSensitive){
            riskScore += 1;
        }
        riskScore += riskScoreFromSeverityScore;
        return riskScore;
    }

    public static List<ApiInfo> getApiInfosFromList(List<BasicDBObject> list, int apiCollectionId){
        List<ApiInfo> apiInfoList = new ArrayList<>();

        Set<ApiInfoKey> apiInfoKeys = new HashSet<ApiInfoKey>();
        for (BasicDBObject singleTypeInfo: list) {
            singleTypeInfo = (BasicDBObject) (singleTypeInfo.getOrDefault("_id", new BasicDBObject()));
            apiInfoKeys.add(new ApiInfoKey(singleTypeInfo.getInt("apiCollectionId"),singleTypeInfo.getString("url"), Method.fromString(singleTypeInfo.getString("method"))));
        }

        BasicDBObject query = new BasicDBObject();
        if (apiCollectionId > -1) {
            query.append(SingleTypeInfo._COLLECTION_IDS, new BasicDBObject("$in", Arrays.asList(apiCollectionId)));
        }

        int counter = 0;
        int batchSize = 100;

        List<String> urlsToSearch = new ArrayList<>();
        
        for(ApiInfoKey apiInfoKey: apiInfoKeys) {
            urlsToSearch.add(apiInfoKey.getUrl());
            counter++;
            if (counter % batchSize == 0 || counter == apiInfoKeys.size()) {
                query.append("_id.url", new BasicDBObject("$in", urlsToSearch));
                List<ApiInfo> fromDb = ApiInfoDao.instance.findAll(query);
                for (ApiInfo a: fromDb) {
                    if (apiInfoKeys.contains(a.getId())) {
                        a.calculateActualAuth();
                        apiInfoList.add(a);
                    }
                }
                urlsToSearch.clear();
            } 
        }
        return apiInfoList;
    }

    public Pair<ApiStats,ApiStats> fetchApiInfoStats(Bson collectionFilter, int startTimestamp, int endTimestamp) {
        ApiStats apiStatsStart = new ApiStats(startTimestamp);
        ApiStats apiStatsEnd = new ApiStats(endTimestamp);

        int totalApis = 0;
        int apisTestedInLookBackPeriod = 0;
        float totalRiskScore = 0;

        // we need only end timestamp filter because data needs to be till end timestamp while start timestamp is for calculating delta
        Bson filter = Filters.and(collectionFilter, Filters.lte(ApiInfo.DISCOVERED_TIMESTAMP, endTimestamp));
        try {
            List<Integer> collectionIds = UsersCollectionsList.getCollectionsIdForUser(Context.userId.get(),Context.accountId.get());
            if (collectionIds != null) {
                filter = Filters.and(Filters.in("collectionIds", collectionIds));
            }
        } catch (Exception e){
        }
        MongoCursor<ApiInfo> cursor = instance.getMCollection().find(filter).cursor();

        while(cursor.hasNext()) {
            ApiInfo apiInfo = cursor.next();
            if (apiInfo.getDiscoveredTimestamp() <= startTimestamp) {
                apiInfo.addStats(apiStatsStart);
                apiStatsStart.setTotalAPIs(apiStatsStart.getTotalAPIs()+1);
                apiStatsStart.setTotalRiskScore(apiStatsStart.getTotalRiskScore() + apiInfo.getRiskScore());
            }

            apiInfo.addStats(apiStatsEnd);
            totalApis += 1;
            totalRiskScore += apiInfo.getRiskScore();
            if (apiInfo.getLastTested() > (Context.now() - 30 * 24 * 60 * 60)) apisTestedInLookBackPeriod += 1;
            String severity = apiInfo.findSeverity();
            apiStatsEnd.addSeverityCount(severity);
        }
        cursor.close();

        apiStatsEnd.setTotalAPIs(totalApis);
        apiStatsEnd.setApisTestedInLookBackPeriod(apisTestedInLookBackPeriod);
        apiStatsEnd.setTotalRiskScore(totalRiskScore);

        return new Pair<>(apiStatsStart, apiStatsEnd);
    }

    public Bson getUpdateFromApiInfo(ApiInfo apiInfo){

        Bson update = Updates.combine(
            Updates.setOnInsert(Constants.ID, apiInfo.getId()),
            Updates.addEachToSet(ApiInfo.ALL_AUTH_TYPES_FOUND, Arrays.asList(apiInfo.getAllAuthTypesFound().toArray())),
            Updates.set(ApiInfo.LAST_SEEN, apiInfo.getLastSeen()),
            Updates.set(ApiInfo.LAST_TESTED, apiInfo.getLastTested()),
            Updates.set(ApiInfo.IS_SENSITIVE, apiInfo.getIsSensitive()),
            Updates.set(ApiInfo.SEVERITY_SCORE, apiInfo.getSeverityScore()),
            Updates.set(ApiInfo.RISK_SCORE, apiInfo.getRiskScore()),
            Updates.set(ApiInfo.LAST_CALCULATED_TIME, apiInfo.getLastCalculatedTime()),
            Updates.set(ApiInfo.API_TYPE, apiInfo.getApiType()),
            Updates.set(ApiInfo.RESPONSE_CODES, apiInfo.getResponseCodes()),
            Updates.addEachToSet(ApiInfo.COLLECTION_IDS, apiInfo.getCollectionIds()),
            Updates.set(ApiInfo.DISCOVERED_TIMESTAMP, apiInfo.getDiscoveredTimestamp())
        );

        Set<ApiInfo.ApiAccessType> apiAccessTypes = apiInfo.getApiAccessTypes();
        if (apiAccessTypes.isEmpty()) {
            update = Updates.combine(update,Updates.setOnInsert(ApiInfo.API_ACCESS_TYPES, new HashSet<>()));
        } else {
            update = Updates.combine(update,Updates.addEachToSet(ApiInfo.API_ACCESS_TYPES, Arrays.asList(apiAccessTypes.toArray())));
        }

        return update;
    }

    @Override
    public String getCollName() {
        return "api_info";
    }

    @Override
    public Class<ApiInfo> getClassT() {
        return ApiInfo.class;
    }

    public static Bson getFilter(ApiInfo.ApiInfoKey apiInfoKey) {
        return getFilter(apiInfoKey.getUrl(), apiInfoKey.getMethod().name(), apiInfoKey.getApiCollectionId());
    }

    public static Bson getFilter(String url, String method, int apiCollectionId) {
        return Filters.and(
                Filters.eq("_id.url", url),
                Filters.eq("_id.method", method),
                Filters.eq("_id.apiCollectionId", apiCollectionId)
        );
    }

    @Override
    public String getFilterKeyString() {
        return TestingEndpoints.getFilterPrefix(ApiCollectionUsers.CollectionType.Id_ApiCollectionId) + ApiInfo.ApiInfoKey.API_COLLECTION_ID;
    }
}
