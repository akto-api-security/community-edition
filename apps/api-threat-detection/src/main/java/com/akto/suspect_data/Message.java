package com.akto.suspect_data;

import com.akto.dto.traffic.SuspectSampleData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

// Kafka Message Wrapper for suspect data
public class Message {
    private String accountId;
    private SuspectSampleData data;
    private int bucketId;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Message() {
    }

    public Message(String accountId, SuspectSampleData data, int bucketId) {
        this.accountId = accountId;
        this.data = data;
        this.bucketId = bucketId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public SuspectSampleData getData() {
        return data;
    }

    public void setData(SuspectSampleData data) {
        this.data = data;
    }

    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    public static Optional<String> marshall(Message m) {
        try {
            return Optional.of(objectMapper.writeValueAsString(m));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> marshall() {
        return marshall(this);
    }

    public static Optional<Message> unmarshall(String s) {
        try {
            return Optional.of(objectMapper.readValue(s, Message.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
