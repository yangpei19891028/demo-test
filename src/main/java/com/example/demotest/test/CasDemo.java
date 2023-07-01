package com.example.demotest.test;

//import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

public class CasDemo {

//    volatile int i = 0;
//    private static Unsafe unsafe;
//
//    volatile int subI = 10;
//
//    static long valueOffsetSub;
//
//    static long valueOffset;
//    static{
//        try {
//            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            unsafe = (Unsafe) theUnsafe.get(null);
//            valueOffset = unsafe.objectFieldOffset(CasDemo.class.getDeclaredField("i"));
//            valueOffsetSub = unsafe.objectFieldOffset(CasDemo.class.getDeclaredField("subI"));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void add(){
//        int current;
//        int value;
//        do{
//            current = unsafe.getIntVolatile(this,valueOffset);
//            value = current + 1;
//        }while (!unsafe.compareAndSwapInt(this,valueOffset,current,value));
//    }
//
//    public void sub(){
//        int current;
//        do{
//            current = unsafe.getIntVolatile(this,valueOffsetSub);
//            System.out.println(Thread.currentThread().getName() + "-----" + current);
//        }while (unsafe.getAndAddInt(this,valueOffsetSub,-1)>0);
//    }
//
//    public static void main(String[] args) throws IOException {
//        CasDemo ld = new CasDemo();
//
////        for (int i = 0; i < 2; i++) { // 2w相加，20000
////            new Thread(() -> {
////                for (int j = 0; j < 10000; j++) {
////                    ld.add();
////                }
////            }).start();
////        }
//
//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                ld.sub();
//            }).start();
//        }
//        //System.in.read(); // 输入任意键退出
//        //System.out.println(ld.i);
//    }
}
