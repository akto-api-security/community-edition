package com.akto.dto.sso;
import com.akto.dto.Config;

public class SAMLConfig extends Config  {

    private String applicationIdentifier;
    private String loginUrl;
    private String x509Certificate;
    private String acsUrl;
    private String entityId;

    public SAMLConfig(){}
    
    public SAMLConfig(ConfigType configType) {
        this.setConfigType(configType);
        String CONFIG_ID = configType.name() + CONFIG_SALT;
        this.setId(CONFIG_ID);
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(String x509Certificate) {
        this.x509Certificate = x509Certificate;
    }

    public String getAcsUrl() {
        return acsUrl;
    }

    public void setAcsUrl(String acsUrl) {
        this.acsUrl = acsUrl;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

}