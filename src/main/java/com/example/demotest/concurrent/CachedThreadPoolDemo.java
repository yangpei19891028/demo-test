package com.example.demotest.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++){
            threadPool.execute(() -> {
                System.out.println("线程池异步执行任务......" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

//            threadPool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("线程池异步执行任务......" + Thread.currentThread().getName());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        }
       // threadPool.shutdown();;
        //threadPool.shutdownNow();
    }
}
