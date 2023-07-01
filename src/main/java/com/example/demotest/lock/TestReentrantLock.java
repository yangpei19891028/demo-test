package com.example.demotest.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    private static int i = 0;

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 10;i++){
                    lock.lock();
                    //lock.tryLock(1, TimeUnit.SECONDS);
                    TestReentrantLock.i++;
                    System.out.println(Thread.currentThread().getName()+":"+TestReentrantLock.i);
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 10;i++){
                    lock.lock();
                    TestReentrantLock.i++;
                    System.out.println(Thread.currentThread().getName()+":"+TestReentrantLock.i);
                    condition.signal();
                    //condition.signalAll();
                    lock.unlock();
                }
            }
        }.start();
        Thread.sleep(1000);
    }
}
