package com.example.demotest.jdk.collection;

import java.util.HashMap;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put(null,null);
        for(int i = 1 ; i <= 9;i++){
            hashMap.put(i*8,"1");
        }
//        hashMap.remove(10);
//        hashMap.get(10);
    }
}
