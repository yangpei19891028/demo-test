package com.example.demotest.caffeine;

import com.github.benmanes.caffeine.cache.*;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test {
    public static void main(String[] args){
        try {
            syncLoad();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            asyncLoad();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
    }

    public static void syncLoad() throws InterruptedException, IOException {
        Cache<String,String> cache = Caffeine.newBuilder()
                //最大容量
                .maximumSize(1024)
                //写入后多长时间过期
                .expireAfterAccess(1, TimeUnit.SECONDS)
//                .refreshAfterWrite(1,TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .removalListener((RemovalListener<String, String>) (key, value, cause) ->
                        System.out.println("key:" + key + ", value:" + value + ", 删除原因:" + cause.toString()))
                .build(new CacheLoader<String, String>() {
                    @Nullable
                    @Override
                    public String load(@NonNull String key) throws Exception {
//                        return null;
                        System.out.println(key);
                        return UUID.randomUUID().toString();
                    }
                });
        cache.put("username","zhangsan");
        cache.put("age","18");
        System.out.println(cache.getIfPresent("username"));
        System.out.println(cache.getIfPresent("age"));
        Thread.sleep(1000);
        System.out.println(cache.getIfPresent("username"));
        System.out.println(cache.getIfPresent("age"));
        System.out.println(cache.get("blob",key->{
            return null;
        }));
        cache.put("username","lisi");
        cache.put("age","19");
        System.out.println(cache.getIfPresent("username"));
        System.out.println(cache.getIfPresent("age"));
    }

    public static void asyncLoad() throws ExecutionException, InterruptedException, TimeoutException {
        AsyncLoadingCache<String,String> cache = Caffeine.newBuilder()
                .maximumSize(1)
                //多长时间过期
//                .expireAfterWrite(5,TimeUnit.SECONDS)
                .expireAfterAccess(5,TimeUnit.MINUTES)
                .weakKeys()
                //多长时间刷新缓存
                .refreshAfterWrite(1,TimeUnit.SECONDS)
                .removalListener((RemovalListener<String, String>) (key, value, cause) ->
                        System.out.println("key:" + key + ", value:" + value + ", 删除原因:" + cause.toString()))
                .buildAsync(new CacheLoader<String, String>() {
                    @Override
                    public @Nullable String reload(@NonNull String key, @NonNull String oldValue) throws Exception {
                        System.out.println("-----------------------------------");
                        return UUID.randomUUID().toString();
                    }
//
                    @Nullable
                    @Override
                    public String load(@NonNull String key) {
                        //System.out.println("key:" + key);
                        return UUID.randomUUID().toString();
                    }
                });
        System.out.println("1-1:"+ cache.get("username").get());
        Thread.sleep(5000);
        System.out.println("1-2:"+ cache.get("username").get());
        Thread.sleep(2000);
        System.out.println("1-3:"+ cache.get("username").get());
        Thread.sleep(2000);
        System.out.println("1-4:"+ cache.get("username").get());
        System.out.println("1-5:"+ cache.get("username").get());
        System.out.println("2:"+ cache.get("password").get(10, TimeUnit.SECONDS));
        System.out.println("3:"+ cache.get("username").get(10, TimeUnit.SECONDS));
        System.out.println("4:"+ cache.get("blog").get());
        System.out.println("5:"+ cache.get("a").get());
    }
}
