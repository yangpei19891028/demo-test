package com.example.demotest.test;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class Test15 {
    public static void main(String[] args) {
//        Test15 t = new Test15();
//        System.out.println(t.getClass() == Test15.class);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(1);
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        completableFuture.whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
                System.out.println(integer);
            }
        });
        System.out.println(2);
    }
}
