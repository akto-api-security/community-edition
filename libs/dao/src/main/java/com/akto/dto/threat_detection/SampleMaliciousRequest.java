package com.akto.dto.threat_detection;

import com.akto.dto.HttpResponseParams;
import com.akto.dto.monitoring.FilterConfig;

import com.akto.dto.type.URLMethods.Method;

import java.util.UUID;

public class SampleMaliciousRequest {

    private String id;
    private String filterId;
    private String actor;
    private String url;
    private Method method;
    private String data;
    private int binId;
    private int expiry;

    public SampleMaliciousRequest() {}

    public SampleMaliciousRequest(
            FilterConfig filter, String actor, HttpResponseParams responseParams) {
        int now = (int) (System.currentTimeMillis() / 1000L);
        this.id = UUID.randomUUID().toString();
        this.filterId = filter.getId();
        this.actor = actor;
        this.data = responseParams.getOrig();
        this.binId = generateBinId(responseParams);
        this.url = responseParams.getRequestParams().getURL();
        this.method = Method.fromString(responseParams.getRequestParams().getMethod());

        // For now, we are hardcoding it to 1 hr.
        // But later we will read it through FilterConfig
        this.expiry = now + (60 * 60);
    }

    public static int generateBinId(HttpResponseParams responseParam) {
        return responseParam.getTime() / 60;
    }

    public String getId() {
        return id;
    }

    public String getFilterId() {
        return filterId;
    }

    public String getActor() {
        return actor;
    }

    public String getData() {
        return data;
    }

    public int getBinId() {
        return binId;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setBinId(int binId) {
        this.binId = binId;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }
}