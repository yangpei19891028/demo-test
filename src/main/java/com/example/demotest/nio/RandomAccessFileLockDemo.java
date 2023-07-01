package com.example.demotest.nio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class RandomAccessFileLockDemo {

    public static void main(String[] args) throws Exception{
        RandomAccessFile file = new RandomAccessFile("D:\\fileChannel1.txt","rw");
        FileChannel channel = file.getChannel();
        //既可以加共享锁，也可以加独占锁，不受限制，不然只能输入流获取的channel可以加共享锁，输出流获取的channel可以加独占锁,共享锁独占锁互斥，
        // 只能一放释放，另一方才能加锁成功，不然会一直阻塞
        FileLock lock = channel.lock(0,Integer.MAX_VALUE,false);
//        channel.lock(0,Integer.MAX_VALUE,false);
        channel.write(ByteBuffer.wrap("123".getBytes()));
        channel.position(0);
        ByteBuffer readBuffer = ByteBuffer.allocateDirect(3);
        channel.read(readBuffer);

        System.out.println(channel.position());

        readBuffer.flip();
        System.out.println(readBuffer.position());
        System.out.println(readBuffer.limit());
        byte[] bytes = new byte[readBuffer.limit()];
        readBuffer.get(bytes);
        System.out.println(new String(bytes));

        lock.release();
        channel.close();
        file.close();
    }
}
