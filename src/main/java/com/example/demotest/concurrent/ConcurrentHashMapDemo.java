package com.example.demotest.concurrent;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put(1,1);
        map.remove(1);
        map.get(1);
        map.size();
        HashMap map1 = new HashMap();
        map1.put(null,1);
    }
}
