package com.stl.ngms.common;

import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class UserSession {

    public static ExpiringMap<String, Object> map = ExpiringMap.builder()
            .expiration(45, TimeUnit.MINUTES)
            .build();

    public static ExpiringMap<String, Object> getMap() {
        return map;
    }

    public static void put(String key, Object value) {
        map.put(key, value);
    }

    public static Object get(String key) {
        return map.get(key);
    }

    public static void remove(String key) {
        map.remove(key);
    }

    public static boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public static boolean containsValue(Object value) {
        return map.containsValue(value);
    }

}
