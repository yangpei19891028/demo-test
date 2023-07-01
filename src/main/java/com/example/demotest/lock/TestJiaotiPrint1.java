package com.example.demotest.lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestJiaotiPrint1 {
    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean flag = new AtomicBoolean(false);
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();

        AtomicInteger count = new AtomicInteger(1);
        new Thread(()->{
            while (count.get() < 100){
                lock.lock();
                try{
                    if(!flag.get()){
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    flag.compareAndSet(true,false);
                    System.out.println(count.getAndAdd(1));
                    conditionB.signal();
                }finally {
                    lock.unlock();
                }
            }
        }).start();
        //Thread.sleep(10);
        new Thread(()->{
            while (count.get() < 100){
                lock.lock();
                try {
                    if(flag.get()){
                        try {
                            conditionB.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    flag.compareAndSet(false,true);
                    System.out.println(count.getAndAdd(1));
                    conditionA.signal();
                }finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
