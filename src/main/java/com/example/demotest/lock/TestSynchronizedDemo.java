package com.example.demotest.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestSynchronizedDemo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Counter counter = new Counter();
        TestSynchronizedDemo_1 t1 = new TestSynchronizedDemo_1(counter,reentrantLock,condition);
        TestSynchronizedDemo_2 t2 = new TestSynchronizedDemo_2(counter,reentrantLock,condition);
        Thread t11 = new Thread(t1);
        Thread t22 = new Thread(t2);
        t11.start();
        t22.start();
    }
}

class Counter{
    int i = 1;
    boolean flag = true;
}

class TestSynchronizedDemo_1 implements Runnable{

    Counter counter;
    ReentrantLock reentrantLock;
    Condition condition;

    public TestSynchronizedDemo_1(Counter counter,ReentrantLock reentrantLock,Condition condition){
        this.counter = counter;
        this.reentrantLock = reentrantLock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (counter.i <= 100){
//            synchronized (counter) {
//                if(counter.flag){
//                    counter.flag = !counter.flag;
//                    System.out.println(Thread.currentThread().getName() + ":" + counter.i++);
//                    counter.notify();
//                }
//                try {
//                    counter.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            reentrantLock.lock();
            if(counter.flag){
                counter.flag = !counter.flag;
                System.out.println(Thread.currentThread().getName() + ":" + counter.i++);
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reentrantLock.unlock();
        }
    }
}

class TestSynchronizedDemo_2 implements Runnable{
    Counter counter;
    ReentrantLock reentrantLock;
    Condition condition;

    public TestSynchronizedDemo_2(Counter counter,ReentrantLock reentrantLock,Condition condition){
        this.counter = counter;
        this.reentrantLock = reentrantLock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (counter.i <= 100){
//            synchronized (counter) {
//                if(!counter.flag){
//                    counter.flag = !counter.flag;
//                    System.out.println(Thread.currentThread().getName() + ":" + counter.i++);
//                    counter.notify();
//                }
//                try {
//                    counter.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            reentrantLock.lock();
            if(!counter.flag){
                counter.flag = !counter.flag;
                System.out.println(Thread.currentThread().getName() + ":" + counter.i++);
                condition.signal();
            }
            reentrantLock.unlock();
        }
    }
}