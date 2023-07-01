package com.example.demotest.test;

public class Test19 {
    public static void main(String[] args) {
        Thread t = new Thread1();
        t.start();
        t.interrupt();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("22222");
    }

    public static class Thread1 extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("1111111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
