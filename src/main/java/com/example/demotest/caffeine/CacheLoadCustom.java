package com.example.demotest.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;

public class CacheLoadCustom implements CacheLoader {

    private CaffeineService caffeineService;

    public CacheLoadCustom(){

    }

    public CacheLoadCustom(CaffeineService caffeineService){
        this.caffeineService = caffeineService;
    }

    @Override
    public Object load(Object o) throws Exception {
        if (o.toString().equalsIgnoreCase("hello")) {
            Thread.sleep(3000);
            return o.toString();
        } else if (o.toString().equalsIgnoreCase("world")) {
            Thread.sleep(5000);
            return o.toString();
        }
        System.out.println(caffeineService);
        return o.toString();
    }

    @Override
    public Object reload(Object key, Object oldValue) throws Exception {
        if (key.toString().equalsIgnoreCase("hello")) {
            return "world";
        } else if (key.toString().equalsIgnoreCase("world")) {
            return "hello";
        }
        return "no value";
    }
}
