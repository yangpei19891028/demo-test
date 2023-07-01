package com.example.demotest.nio;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileChannelLockDemo {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("D:\\fileChannel.txt");
        FileChannel fileChannel = inputStream.getChannel();
        fileChannel.lock(0,Integer.MAX_VALUE,true);
        System.out.println("加共享锁成功");
//        Thread.sleep(3600 * 1000);
        fileChannel.close();
        inputStream.close();
    }
}
