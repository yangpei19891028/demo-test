package com.example.demotest.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CaffeinConfig {

    @Bean
    public Caffeine caffeine(){
        Caffeine caffeine = Caffeine.newBuilder()
                .maximumSize(1024)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .refreshAfterWrite(1,TimeUnit.SECONDS)
                .removalListener((RemovalListener<String, String>) (key, value, cause) ->
                        System.out.println("key:" + key + ", value:" + value + ", 删除原因:" + cause.toString()));
        return caffeine;
    }

    @Bean
    public CacheLoadCustom cacheLoadCustom(CaffeineService caffeineService){
        return new CacheLoadCustom(caffeineService);
    }

//    @Bean
//    public CacheManager cacheManager(Caffeine caffeine,CacheLoadCustom cacheLoadCustom){
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//        caffeineCacheManager.setCaffeine(caffeine);
//        caffeineCacheManager.setCacheLoader(cacheLoadCustom);
//        return caffeineCacheManager;
//    }
}
