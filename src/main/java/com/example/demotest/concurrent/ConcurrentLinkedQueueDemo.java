package com.example.demotest.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.peek();
        queue.poll();
        queue.add("2");
        queue.remove("1");
        queue.size();
        queue.iterator();
    }
}
