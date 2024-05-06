package com.akto.data_actor;

import com.akto.dto.*;
import com.akto.dto.billing.Organization;
import com.akto.dto.runtime_filters.RuntimeFilter;
import com.akto.dto.traffic.SampleData;
import com.akto.dto.traffic.TrafficInfo;
import com.akto.dto.traffic_metrics.TrafficMetrics;
import com.akto.dto.type.SingleTypeInfo;
import com.mongodb.client.model.WriteModel;

import java.util.ArrayList;
import java.util.List;

public class DbActor extends DataActor {

    public AccountSettings fetchAccountSettings() {
        return DbLayer.fetchAccountSettings();
    }

    public long fetchEstimatedDocCount() {
        return DbLayer.fetchEstimatedDocCount();
    }

    public void updateCidrList(List<String> cidrList) {
        DbLayer.updateCidrList(cidrList);
    }

    public void updateApiCollectionNameForVxlan(int vxlanId, String name) {
        DbLayer.updateApiCollectionName(vxlanId, name);
    }

    public APIConfig fetchApiConfig(String configName) {
        return DbLayer.fetchApiconfig(configName);
    }

    public void bulkWriteSingleTypeInfo(List<Object> writesForApiInfo) {
        ArrayList<WriteModel<SingleTypeInfo>> writes = new ArrayList<>();
        for (Object obj: writesForApiInfo) {
            WriteModel<SingleTypeInfo> write = (WriteModel<SingleTypeInfo>)obj;
            writes.add(write);
        }
        DbLayer.bulkWriteSingleTypeInfo(writes);
    }

    public void bulkWriteSensitiveParamInfo(List<Object> writesForSensitiveParamInfo) {
        ArrayList<WriteModel<SensitiveParamInfo>> writes = new ArrayList<>();
        for (Object obj: writesForSensitiveParamInfo) {
            WriteModel<SensitiveParamInfo> write = (WriteModel<SensitiveParamInfo>)obj;
            writes.add(write);
        }
        DbLayer.bulkWriteSensitiveParamInfo(writes);
    }

    public void bulkWriteSampleData(List<Object> writesForSampleData) {
        ArrayList<WriteModel<SampleData>> writes = new ArrayList<>();
        for (Object obj: writesForSampleData) {
            WriteModel<SampleData> write = (WriteModel<SampleData>)obj;
            writes.add(write);
        }
        DbLayer.bulkWriteSampleData(writes);
    }

    public void bulkWriteSensitiveSampleData(List<Object> writesForSensitiveSampleData) {
        ArrayList<WriteModel<SensitiveSampleData>> writes = new ArrayList<>();
        for (Object obj: writesForSensitiveSampleData) {
            WriteModel<SensitiveSampleData> write = (WriteModel<SensitiveSampleData>)obj;
            writes.add(write);
        }
        DbLayer.bulkWriteSensitiveSampleData(writes);
    }

    public void bulkWriteTrafficInfo(List<Object> writesForTrafficInfo) {
        ArrayList<WriteModel<TrafficInfo>> writes = new ArrayList<>();
        for (Object obj: writesForTrafficInfo) {
            WriteModel<TrafficInfo> write = (WriteModel<TrafficInfo>)obj;
            writes.add(write);
        }
        DbLayer.bulkWriteTrafficInfo(writes);
    }

    public void bulkWriteTrafficMetrics(List<Object> writesForTrafficMetrics) {
        ArrayList<WriteModel<TrafficMetrics>> writes = new ArrayList<>();
        for (Object obj: writesForTrafficMetrics) {
            WriteModel<TrafficMetrics> write = (WriteModel<TrafficMetrics>)obj;
            writes.add(write);
        }
        DbLayer.bulkWriteTrafficMetrics(writes);
    }

    public List<SingleTypeInfo> fetchStiOfCollections(int batchCount, int lastStiFetchTs) {
        return DbLayer.fetchStiOfCollections();
    }

    public List<SingleTypeInfo> fetchAllStis(int batchCount, int lastStiFetchTs) {
        List<SingleTypeInfo> allParams = DbLayer.fetchStiBasedOnHostHeaders();
        allParams.addAll(DbLayer.fetchAllSingleTypeInfo());
        return allParams;
    }

    public List<SensitiveParamInfo> getUnsavedSensitiveParamInfos() {
        return DbLayer.getUnsavedSensitiveParamInfos();
    }

    public List<CustomDataType> fetchCustomDataTypes() {
        return DbLayer.fetchCustomDataTypes();
    }

    public List<AktoDataType> fetchAktoDataTypes() {
        return DbLayer.fetchAktoDataTypes();
    }

    public List<CustomAuthType> fetchCustomAuthTypes() {
        return DbLayer.fetchCustomAuthTypes();
    }

    public List<ApiInfo> fetchApiInfos() {
        return DbLayer.fetchApiInfos();
    }

    public List<ApiInfo> fetchNonTrafficApiInfos() {
        return DbLayer.fetchNonTrafficApiInfos();
    }

    public void bulkWriteApiInfo(List<ApiInfo> apiInfoList) {
        DbLayer.bulkWriteApiInfo(apiInfoList);
    }
    public List<RuntimeFilter> fetchRuntimeFilters() {
        return DbLayer.fetchRuntimeFilters();
    }

    public void updateRuntimeVersion(String fieldName, String version) {
        DbLayer.updateRuntimeVersion(fieldName, version);
    }

    public Account fetchActiveAccount() {
        return DbLayer.fetchActiveAccount();
    }

    public void updateKafkaIp(String currentInstanceIp) {
        DbLayer.updateKafkaIp(currentInstanceIp);
    }

    public List<ApiInfo.ApiInfoKey> fetchEndpointsInCollection() {
        return DbLayer.fetchEndpointsInCollection();
    }

    public List<ApiCollection> fetchApiCollections() {
        return DbLayer.fetchApiCollections();
    }

    public void createCollectionSimple(int vxlanId) {
        DbLayer.createCollectionSimple(vxlanId);
    }

    public void createCollectionForHost(String host, int colId) {
        DbLayer.createCollectionForHost(host, colId);
    }

    public AccountSettings fetchAccountSettingsForAccount(int accountId) {
        return DbLayer.fetchAccountSettings(accountId);
    }

    public void insertRuntimeLog(Log log) {
        DbLayer.insertRuntimeLog(log);
    }

    public void insertAnalyserLog(Log log) {
        DbLayer.insertAnalyserLog(log);
    }

    public void modifyHybridSaasSetting(boolean isHybridSaas) {
        DbLayer.modifyHybridSaasSetting(isHybridSaas);
    }

    public Setup fetchSetup() {
        return DbLayer.fetchSetup();
    }

    public Organization fetchOrganization(int accountId) {
        return DbLayer.fetchOrganization(accountId);
    }
}
