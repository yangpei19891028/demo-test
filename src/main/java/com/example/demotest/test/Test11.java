package com.example.demotest.test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Test11 {

    public static void main(String[] args) throws IOException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("11111");
            return 1;
        });
        completableFuture.whenComplete((v,t)->{
            System.out.println(t + "qqqqq");
            System.out.println(v + "wwwww");
        });
        System.in.read();
    }
}
