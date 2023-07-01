package com.example.demotest.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class TestJiaotiPrint {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        AtomicInteger count = new AtomicInteger(1);
        new Thread(()->{
            while(count.get() < 299){
                synchronized (o){
                    if(count.get() % 3 == 0){
                        System.out.print(count.getAndAdd(1));
                        System.out.println("A");
                    }
                }
            }
        }).start();
        Thread.sleep(10);
        new Thread(()->{
            while(count.get() < 299){
                synchronized (o){
                    if(count.get() % 3 == 1){
                        System.out.print(count.getAndAdd(1));
                        System.out.println("B");
                    }
                }
            }
        }).start();
        Thread.sleep(10);
        new Thread(()->{
            while(count.get()<299){
                synchronized (o){
                    if(count.get() % 3 == 2){
                        System.out.print(count.getAndAdd(1));
                        System.out.println("C");
                    }
                }
            }
        }).start();
    }
}
