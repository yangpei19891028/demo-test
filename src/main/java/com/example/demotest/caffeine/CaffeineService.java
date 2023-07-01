package com.example.demotest.caffeine;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CaffeineService {

    public static final String CACHE_NAME = "local";

    @Cacheable(value = CACHE_NAME, key = "'key_'+#id")
    public String findById(Long id){
        return "find value:" + id;
    }

    @CachePut(value = CACHE_NAME, key = "'key_'+#id")
    public String saveProductInfo(Long id) {
        return "put value:" + id;
    }
}
