package com.example.demotest.sentinel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.LongAdder;

public class SlotWindow {

    public static LongAdder counter = new LongAdder();

    static LinkedList<Long> slots = new LinkedList<>();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                check();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        while(true){
            //判断限流
            counter.increment();
            try {
                Thread.sleep(new Random().nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void check() throws Exception{
        while(true){
            Thread.sleep(100);
            slots.add(counter.sum());
            if(slots.size() > 10){
                slots.removeFirst();
            }
            if(slots.peekLast() - slots.peekFirst() > 100){
                System.out.println("限流了");
            }else{
                System.out.println("正常放行");
            }
        }
    }
}
