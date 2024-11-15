package com.akto.cache;

public interface CounterCache {

    void incrementBy(String key, long val);

    void increment(String key);

    long get(String key);

    boolean exists(String key);
}
