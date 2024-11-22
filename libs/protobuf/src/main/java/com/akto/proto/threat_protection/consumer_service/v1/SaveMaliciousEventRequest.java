// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: threat_protection/consumer_service/v1/consumer_service.proto
// Protobuf Java Version: 4.28.3

package com.akto.proto.threat_protection.consumer_service.v1;

/**
 * Protobuf type {@code threat_protection.consumer_service.v1.SaveMaliciousEventRequest}
 */
public final class SaveMaliciousEventRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:threat_protection.consumer_service.v1.SaveMaliciousEventRequest)
    SaveMaliciousEventRequestOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 28,
      /* patch= */ 3,
      /* suffix= */ "",
      SaveMaliciousEventRequest.class.getName());
  }
  // Use SaveMaliciousEventRequest.newBuilder() to construct.
  private SaveMaliciousEventRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private SaveMaliciousEventRequest() {
    events_ = java.util.Collections.emptyList();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SaveMaliciousEventRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SaveMaliciousEventRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest.class, com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest.Builder.class);
  }

  public static final int EVENTS_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private java.util.List<com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent> events_;
  /**
   * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
   */
  @java.lang.Override
  public java.util.List<com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent> getEventsList() {
    return events_;
  }
  /**
   * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.akto.proto.threat_protection.consumer_service.v1.MaliciousEventOrBuilder> 
      getEventsOrBuilderList() {
    return events_;
  }
  /**
   * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
   */
  @java.lang.Override
  public int getEventsCount() {
    return events_.size();
  }
  /**
   * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
   */
  @java.lang.Override
  public com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent getEvents(int index) {
    return events_.get(index);
  }
  /**
   * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
   */
  @java.lang.Override
  public com.akto.proto.threat_protection.consumer_service.v1.MaliciousEventOrBuilder getEventsOrBuilder(
      int index) {
    return events_.get(index);
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
    for (int i = 0; i < events_.size(); i++) {
      output.writeMessage(2, events_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < events_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, events_.get(i));
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
    if (!(obj instanceof com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest)) {
      return super.equals(obj);
    }
    com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest other = (com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest) obj;

    if (!getEventsList()
        .equals(other.getEventsList())) return false;
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
    if (getEventsCount() > 0) {
      hash = (37 * hash) + EVENTS_FIELD_NUMBER;
      hash = (53 * hash) + getEventsList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest parseFrom(
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
  public static Builder newBuilder(com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest prototype) {
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
   * Protobuf type {@code threat_protection.consumer_service.v1.SaveMaliciousEventRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:threat_protection.consumer_service.v1.SaveMaliciousEventRequest)
      com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SaveMaliciousEventRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SaveMaliciousEventRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest.class, com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest.Builder.class);
    }

    // Construct using com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest.newBuilder()
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
      if (eventsBuilder_ == null) {
        events_ = java.util.Collections.emptyList();
      } else {
        events_ = null;
        eventsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.akto.proto.threat_protection.consumer_service.v1.ConsumerServiceProto.internal_static_threat_protection_consumer_service_v1_SaveMaliciousEventRequest_descriptor;
    }

    @java.lang.Override
    public com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest getDefaultInstanceForType() {
      return com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest build() {
      com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest buildPartial() {
      com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest result = new com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest result) {
      if (eventsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          events_ = java.util.Collections.unmodifiableList(events_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.events_ = events_;
      } else {
        result.events_ = eventsBuilder_.build();
      }
    }

    private void buildPartial0(com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest) {
        return mergeFrom((com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest other) {
      if (other == com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest.getDefaultInstance()) return this;
      if (eventsBuilder_ == null) {
        if (!other.events_.isEmpty()) {
          if (events_.isEmpty()) {
            events_ = other.events_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureEventsIsMutable();
            events_.addAll(other.events_);
          }
          onChanged();
        }
      } else {
        if (!other.events_.isEmpty()) {
          if (eventsBuilder_.isEmpty()) {
            eventsBuilder_.dispose();
            eventsBuilder_ = null;
            events_ = other.events_;
            bitField0_ = (bitField0_ & ~0x00000001);
            eventsBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getEventsFieldBuilder() : null;
          } else {
            eventsBuilder_.addAllMessages(other.events_);
          }
        }
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
            case 18: {
              com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent m =
                  input.readMessage(
                      com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.parser(),
                      extensionRegistry);
              if (eventsBuilder_ == null) {
                ensureEventsIsMutable();
                events_.add(m);
              } else {
                eventsBuilder_.addMessage(m);
              }
              break;
            } // case 18
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

    private java.util.List<com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent> events_ =
      java.util.Collections.emptyList();
    private void ensureEventsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        events_ = new java.util.ArrayList<com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent>(events_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEventOrBuilder> eventsBuilder_;

    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public java.util.List<com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent> getEventsList() {
      if (eventsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(events_);
      } else {
        return eventsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public int getEventsCount() {
      if (eventsBuilder_ == null) {
        return events_.size();
      } else {
        return eventsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent getEvents(int index) {
      if (eventsBuilder_ == null) {
        return events_.get(index);
      } else {
        return eventsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder setEvents(
        int index, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent value) {
      if (eventsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEventsIsMutable();
        events_.set(index, value);
        onChanged();
      } else {
        eventsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder setEvents(
        int index, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder builderForValue) {
      if (eventsBuilder_ == null) {
        ensureEventsIsMutable();
        events_.set(index, builderForValue.build());
        onChanged();
      } else {
        eventsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder addEvents(com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent value) {
      if (eventsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEventsIsMutable();
        events_.add(value);
        onChanged();
      } else {
        eventsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder addEvents(
        int index, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent value) {
      if (eventsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEventsIsMutable();
        events_.add(index, value);
        onChanged();
      } else {
        eventsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder addEvents(
        com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder builderForValue) {
      if (eventsBuilder_ == null) {
        ensureEventsIsMutable();
        events_.add(builderForValue.build());
        onChanged();
      } else {
        eventsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder addEvents(
        int index, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder builderForValue) {
      if (eventsBuilder_ == null) {
        ensureEventsIsMutable();
        events_.add(index, builderForValue.build());
        onChanged();
      } else {
        eventsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder addAllEvents(
        java.lang.Iterable<? extends com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent> values) {
      if (eventsBuilder_ == null) {
        ensureEventsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, events_);
        onChanged();
      } else {
        eventsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder clearEvents() {
      if (eventsBuilder_ == null) {
        events_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        eventsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public Builder removeEvents(int index) {
      if (eventsBuilder_ == null) {
        ensureEventsIsMutable();
        events_.remove(index);
        onChanged();
      } else {
        eventsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder getEventsBuilder(
        int index) {
      return getEventsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public com.akto.proto.threat_protection.consumer_service.v1.MaliciousEventOrBuilder getEventsOrBuilder(
        int index) {
      if (eventsBuilder_ == null) {
        return events_.get(index);  } else {
        return eventsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public java.util.List<? extends com.akto.proto.threat_protection.consumer_service.v1.MaliciousEventOrBuilder> 
         getEventsOrBuilderList() {
      if (eventsBuilder_ != null) {
        return eventsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(events_);
      }
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder addEventsBuilder() {
      return getEventsFieldBuilder().addBuilder(
          com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.getDefaultInstance());
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder addEventsBuilder(
        int index) {
      return getEventsFieldBuilder().addBuilder(
          index, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.getDefaultInstance());
    }
    /**
     * <code>repeated .threat_protection.consumer_service.v1.MaliciousEvent events = 2 [json_name = "events"];</code>
     */
    public java.util.List<com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder> 
         getEventsBuilderList() {
      return getEventsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEventOrBuilder> 
        getEventsFieldBuilder() {
      if (eventsBuilder_ == null) {
        eventsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEvent.Builder, com.akto.proto.threat_protection.consumer_service.v1.MaliciousEventOrBuilder>(
                events_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        events_ = null;
      }
      return eventsBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:threat_protection.consumer_service.v1.SaveMaliciousEventRequest)
  }

  // @@protoc_insertion_point(class_scope:threat_protection.consumer_service.v1.SaveMaliciousEventRequest)
  private static final com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest();
  }

  public static com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SaveMaliciousEventRequest>
      PARSER = new com.google.protobuf.AbstractParser<SaveMaliciousEventRequest>() {
    @java.lang.Override
    public SaveMaliciousEventRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<SaveMaliciousEventRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SaveMaliciousEventRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.akto.proto.threat_protection.consumer_service.v1.SaveMaliciousEventRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

