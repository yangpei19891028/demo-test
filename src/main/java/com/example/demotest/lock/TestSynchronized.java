package com.example.demotest.lock;

public class TestSynchronized {
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 10;i++){
                    add();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 10;i++){
                    add();
                }
            }
        }.start();
        Thread.sleep(2000);
        System.out.println(i);
    }

    public synchronized static void add(){
        i++;
    }
}
