package com.example.demotest.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("张三");
        queue.poll();
        queue.put("李四");
        queue.take();
        queue.iterator();
        queue.size();
    }

//    public void test() throws InterruptedException {
//        LinkedBlockingQueue<Long> queue = new LinkedBlockingQueue<>();
//        Long s = queue.take();
//        int PAGE_SIZE = 4 * 1024 * 1024;
//        Long flushedPosition = 0L;
//        Long wrotePosition = 0L;
//        wrotePosition = s;
//        wrotePosition - flushedPosition >= PAGE_SIZE
//    }

}
