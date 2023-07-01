package com.example.demotest.thread;

public class MyThread extends Thread {
    public void run(){
        super.run();
        for(int i=0; i<100000; i++){
            try {
                Thread.sleep(1111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i="+(i+1));
        }
    }
}
