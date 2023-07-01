package com.example.demotest.test;

public class Test3 {

    public static void main(String[] args) throws Exception{
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        t.interrupt();
        Thread.sleep(1000);
        System.out.println(Thread.interrupted());
    }
}
