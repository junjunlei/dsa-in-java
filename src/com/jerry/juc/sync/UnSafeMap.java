package com.jerry.juc.sync;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author jerry
 * @Description Map线程不安全问题
 * @Date 2022-05-10 14:13
 * @Version 1.0
 **/
public class UnSafeMap {
    public static void main(String[] args) {
        //ConcurrentModificationException
        Map<String, String> map = new HashMap<>();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 50; i++) {
            String key = UUID.randomUUID().toString().substring(0, 8);
            map.put(key, key);
            System.out.println(map);
        }
    }
}
