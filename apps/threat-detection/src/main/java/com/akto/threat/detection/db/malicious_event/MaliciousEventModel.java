package com.akto.threat.detection.db.malicious_event;

import com.akto.dto.type.URLMethods;

import java.util.UUID;

public class MaliciousEventModel {

  private String id;
  private String actorId;
  private String filterId;
  private String url;
  private URLMethods.Method method;
  private long timestamp;
  private String orig;

  // Geo location data
  private String ip;
  private String country;

  public MaliciousEventModel() {}

  public MaliciousEventModel(Builder builder) {
    this.id = builder.id == null ? UUID.randomUUID().toString() : builder.id;
    this.actorId = builder.actorId;
    this.filterId = builder.filterId;
    this.url = builder.url;
    this.method = builder.method;
    this.timestamp = builder.timestamp;
    this.orig = builder.orig;
    this.ip = builder.ip;
    this.country = builder.country;
  }

  public static class Builder {
    private String id;
    private String actorId;
    private String filterId;
    private String url;
    private URLMethods.Method method;
    private long timestamp;
    private String orig;
    private String ip;
    private String country;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setActorId(String actorId) {
      this.actorId = actorId;
      return this;
    }

    public Builder setFilterId(String filterId) {
      this.filterId = filterId;
      return this;
    }

    public Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder setMethod(URLMethods.Method method) {
      this.method = method;
      return this;
    }

    public Builder setTimestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder setOrig(String orig) {
      this.orig = orig;
      return this;
    }

    public Builder setIp(String ip) {
      this.ip = ip;
      return this;
    }

    public Builder setCountry(String country) {
      this.country = country;
      return this;
    }

    public MaliciousEventModel build() {
      return new MaliciousEventModel(this);
    }
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getId() {
    return id;
  }

  public String getActorId() {
    return actorId;
  }

  public String getFilterId() {
    return filterId;
  }

  public String getUrl() {
    return url;
  }

  public URLMethods.Method getMethod() {
    return method;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getOrig() {
    return orig;
  }

  public String getIp() {
    return ip;
  }

  public String getCountry() {
    return country;
  }

  @Override
  public String toString() {
    return "MaliciousEventModel{"
        + "ip='"
        + ip
        + '\''
        + ", id='"
        + id
        + '\''
        + ", actorId='"
        + actorId
        + '\''
        + ", filterId='"
        + filterId
        + '\''
        + ", url='"
        + url
        + '\''
        + ", method="
        + method
        + ", timestamp="
        + timestamp
        + ", country='"
        + country
        + '\''
        + '}';
  }
}