package com.example.demotest.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(ArrayBlockingQueueDemo.class.getClassLoader());
        ArrayBlockingQueueDemo arrayBlockingQueueDemo = new ArrayBlockingQueueDemo();
        System.out.println(arrayBlockingQueueDemo.getClass().getClassLoader());
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        queue.offer("1");
        queue.poll();
        queue.put("2");
        queue.take();
        queue.size();
    }
}
