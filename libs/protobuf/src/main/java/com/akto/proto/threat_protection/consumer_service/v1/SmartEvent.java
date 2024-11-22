// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: threat_protection/consumer_service/v1/consumer_service.proto
// Protobuf Java Version: 4.28.3

package com.akto.proto.threat_protection.consumer_service.v1;

/**
 * Protobuf type {@code threat_protection.consumer_service.v1.SmartEvent}
 */
public final class SmartEvent extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:threat_protection.consumer_service.v1.SmartEvent)
    SmartEventOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 28,
      /* patch= */ 3,
      /* suffix= */ "",
      SmartEvent.class.getName());
  }
  // Use SmartEvent.newBuilder() to construct.
  private SmartEvent(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private SmartEvent() {
    actorId_ = "";
    filterId_ = "";
    ruleId_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SmartEvent_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SmartEvent_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.akto.proto.threat_protection.consumer_service.v1.SmartEvent.class, com.akto.proto.threat_protection.consumer_service.v1.SmartEvent.Builder.class);
  }

  public static final int ACTOR_ID_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object actorId_ = "";
  /**
   * <code>string actor_id = 1 [json_name = "actorId"];</code>
   * @return The actorId.
   */
  @java.lang.Override
  public java.lang.String getActorId() {
    java.lang.Object ref = actorId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      actorId_ = s;
      return s;
    }
  }
  /**
   * <code>string actor_id = 1 [json_name = "actorId"];</code>
   * @return The bytes for actorId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getActorIdBytes() {
    java.lang.Object ref = actorId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      actorId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FILTER_ID_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object filterId_ = "";
  /**
   * <code>string filter_id = 2 [json_name = "filterId"];</code>
   * @return The filterId.
   */
  @java.lang.Override
  public java.lang.String getFilterId() {
    java.lang.Object ref = filterId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      filterId_ = s;
      return s;
    }
  }
  /**
   * <code>string filter_id = 2 [json_name = "filterId"];</code>
   * @return The bytes for filterId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getFilterIdBytes() {
    java.lang.Object ref = filterId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      filterId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DETECTED_AT_FIELD_NUMBER = 3;
  private long detectedAt_ = 0L;
  /**
   * <code>int64 detected_at = 3 [json_name = "detectedAt"];</code>
   * @return The detectedAt.
   */
  @java.lang.Override
  public long getDetectedAt() {
    return detectedAt_;
  }

  public static final int RULE_ID_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private volatile java.lang.Object ruleId_ = "";
  /**
   * <code>string rule_id = 4 [json_name = "ruleId"];</code>
   * @return The ruleId.
   */
  @java.lang.Override
  public java.lang.String getRuleId() {
    java.lang.Object ref = ruleId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      ruleId_ = s;
      return s;
    }
  }
  /**
   * <code>string rule_id = 4 [json_name = "ruleId"];</code>
   * @return The bytes for ruleId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getRuleIdBytes() {
    java.lang.Object ref = ruleId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      ruleId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(actorId_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, actorId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filterId_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, filterId_);
    }
    if (detectedAt_ != 0L) {
      output.writeInt64(3, detectedAt_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(ruleId_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 4, ruleId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(actorId_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, actorId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(filterId_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, filterId_);
    }
    if (detectedAt_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, detectedAt_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(ruleId_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(4, ruleId_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.akto.proto.threat_protection.consumer_service.v1.SmartEvent)) {
      return super.equals(obj);
    }
    com.akto.proto.threat_protection.consumer_service.v1.SmartEvent other = (com.akto.proto.threat_protection.consumer_service.v1.SmartEvent) obj;

    if (!getActorId()
        .equals(other.getActorId())) return false;
    if (!getFilterId()
        .equals(other.getFilterId())) return false;
    if (getDetectedAt()
        != other.getDetectedAt()) return false;
    if (!getRuleId()
        .equals(other.getRuleId())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ACTOR_ID_FIELD_NUMBER;
    hash = (53 * hash) + getActorId().hashCode();
    hash = (37 * hash) + FILTER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getFilterId().hashCode();
    hash = (37 * hash) + DETECTED_AT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDetectedAt());
    hash = (37 * hash) + RULE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getRuleId().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.akto.proto.threat_protection.consumer_service.v1.SmartEvent prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code threat_protection.consumer_service.v1.SmartEvent}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:threat_protection.consumer_service.v1.SmartEvent)
      com.akto.proto.threat_protection.consumer_service.v1.SmartEventOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SmartEvent_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SmartEvent_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.akto.proto.threat_protection.consumer_service.v1.SmartEvent.class, com.akto.proto.threat_protection.consumer_service.v1.SmartEvent.Builder.class);
    }

    // Construct using com.akto.proto.threat_protection.consumer_service.v1.SmartEvent.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      actorId_ = "";
      filterId_ = "";
      detectedAt_ = 0L;
      ruleId_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SmartEvent_descriptor;
    }

    @java.lang.Override
    public com.akto.proto.threat_protection.consumer_service.v1.SmartEvent getDefaultInstanceForType() {
      return com.akto.proto.threat_protection.consumer_service.v1.SmartEvent.getDefaultInstance();
    }

    @java.lang.Override
    public com.akto.proto.threat_protection.consumer_service.v1.SmartEvent build() {
      com.akto.proto.threat_protection.consumer_service.v1.SmartEvent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.akto.proto.threat_protection.consumer_service.v1.SmartEvent buildPartial() {
      com.akto.proto.threat_protection.consumer_service.v1.SmartEvent result = new com.akto.proto.threat_protection.consumer_service.v1.SmartEvent(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.akto.proto.threat_protection.consumer_service.v1.SmartEvent result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.actorId_ = actorId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.filterId_ = filterId_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.detectedAt_ = detectedAt_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.ruleId_ = ruleId_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.akto.proto.threat_protection.consumer_service.v1.SmartEvent) {
        return mergeFrom((com.akto.proto.threat_protection.consumer_service.v1.SmartEvent)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.akto.proto.threat_protection.consumer_service.v1.SmartEvent other) {
      if (other == com.akto.proto.threat_protection.consumer_service.v1.SmartEvent.getDefaultInstance()) return this;
      if (!other.getActorId().isEmpty()) {
        actorId_ = other.actorId_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getFilterId().isEmpty()) {
        filterId_ = other.filterId_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.getDetectedAt() != 0L) {
        setDetectedAt(other.getDetectedAt());
      }
      if (!other.getRuleId().isEmpty()) {
        ruleId_ = other.ruleId_;
        bitField0_ |= 0x00000008;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              actorId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              filterId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              detectedAt_ = input.readInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 34: {
              ruleId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object actorId_ = "";
    /**
     * <code>string actor_id = 1 [json_name = "actorId"];</code>
     * @return The actorId.
     */
    public java.lang.String getActorId() {
      java.lang.Object ref = actorId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        actorId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string actor_id = 1 [json_name = "actorId"];</code>
     * @return The bytes for actorId.
     */
    public com.google.protobuf.ByteString
        getActorIdBytes() {
      java.lang.Object ref = actorId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        actorId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string actor_id = 1 [json_name = "actorId"];</code>
     * @param value The actorId to set.
     * @return This builder for chaining.
     */
    public Builder setActorId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      actorId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string actor_id = 1 [json_name = "actorId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearActorId() {
      actorId_ = getDefaultInstance().getActorId();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string actor_id = 1 [json_name = "actorId"];</code>
     * @param value The bytes for actorId to set.
     * @return This builder for chaining.
     */
    public Builder setActorIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      actorId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object filterId_ = "";
    /**
     * <code>string filter_id = 2 [json_name = "filterId"];</code>
     * @return The filterId.
     */
    public java.lang.String getFilterId() {
      java.lang.Object ref = filterId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        filterId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string filter_id = 2 [json_name = "filterId"];</code>
     * @return The bytes for filterId.
     */
    public com.google.protobuf.ByteString
        getFilterIdBytes() {
      java.lang.Object ref = filterId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        filterId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string filter_id = 2 [json_name = "filterId"];</code>
     * @param value The filterId to set.
     * @return This builder for chaining.
     */
    public Builder setFilterId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      filterId_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string filter_id = 2 [json_name = "filterId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearFilterId() {
      filterId_ = getDefaultInstance().getFilterId();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string filter_id = 2 [json_name = "filterId"];</code>
     * @param value The bytes for filterId to set.
     * @return This builder for chaining.
     */
    public Builder setFilterIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      filterId_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private long detectedAt_ ;
    /**
     * <code>int64 detected_at = 3 [json_name = "detectedAt"];</code>
     * @return The detectedAt.
     */
    @java.lang.Override
    public long getDetectedAt() {
      return detectedAt_;
    }
    /**
     * <code>int64 detected_at = 3 [json_name = "detectedAt"];</code>
     * @param value The detectedAt to set.
     * @return This builder for chaining.
     */
    public Builder setDetectedAt(long value) {

      detectedAt_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int64 detected_at = 3 [json_name = "detectedAt"];</code>
     * @return This builder for chaining.
     */
    public Builder clearDetectedAt() {
      bitField0_ = (bitField0_ & ~0x00000004);
      detectedAt_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object ruleId_ = "";
    /**
     * <code>string rule_id = 4 [json_name = "ruleId"];</code>
     * @return The ruleId.
     */
    public java.lang.String getRuleId() {
      java.lang.Object ref = ruleId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        ruleId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string rule_id = 4 [json_name = "ruleId"];</code>
     * @return The bytes for ruleId.
     */
    public com.google.protobuf.ByteString
        getRuleIdBytes() {
      java.lang.Object ref = ruleId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        ruleId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string rule_id = 4 [json_name = "ruleId"];</code>
     * @param value The ruleId to set.
     * @return This builder for chaining.
     */
    public Builder setRuleId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      ruleId_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>string rule_id = 4 [json_name = "ruleId"];</code>
     * @return This builder for chaining.
     */
    public Builder clearRuleId() {
      ruleId_ = getDefaultInstance().getRuleId();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>string rule_id = 4 [json_name = "ruleId"];</code>
     * @param value The bytes for ruleId to set.
     * @return This builder for chaining.
     */
    public Builder setRuleIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      ruleId_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:threat_protection.consumer_service.v1.SmartEvent)
  }

  // @@protoc_insertion_point(class_scope:threat_protection.consumer_service.v1.SmartEvent)
  private static final com.akto.proto.threat_protection.consumer_service.v1.SmartEvent DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.akto.proto.threat_protection.consumer_service.v1.SmartEvent();
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SmartEvent getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SmartEvent>
      PARSER = new com.google.protobuf.AbstractParser<SmartEvent>() {
    @java.lang.Override
    public SmartEvent parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<SmartEvent> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SmartEvent> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.akto.proto.threat_protection.consumer_service.v1.SmartEvent getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

