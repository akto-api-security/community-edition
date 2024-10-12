package com.akto.dao.audit_logs;

import com.akto.dao.AccountsContextDao;
import com.akto.dao.MCollection;
import com.akto.dto.audit_logs.ApiAuditLogs;

public class ApiAuditLogsDao extends AccountsContextDao<ApiAuditLogs> {

    public static final ApiAuditLogsDao instance = new ApiAuditLogsDao();

    private ApiAuditLogsDao() {}

    public void createIndicesIfAbsent() {
        String[] fieldNames = { ApiAuditLogs.TIMESTAMP };
        MCollection.createIndexIfAbsent(getDBName(), getCollName(), fieldNames, true);
    }

    @Override
    public String getCollName() {
        return "api_audit_logs";
    }

    @Override
    public Class<ApiAuditLogs> getClassT() {
        return ApiAuditLogs.class;
    }
}