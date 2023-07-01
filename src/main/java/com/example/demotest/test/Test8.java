package com.example.demotest.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;

public class Test8 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("D:\\mappedByteBuffer.txt","rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel channel = file.getChannel();
        MappedByteBuffer mappedByteBuffer = null;
        try {
            mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1L * 1024 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mappedByteBuffer.position(15);
//        byte[] b = "890".getBytes();
//        mappedByteBuffer.putInt(b.length);
//        mappedByteBuffer.put(b);
//        System.out.println(mappedByteBuffer.capacity());
//        System.out.println(mappedByteBuffer.position());
//        System.out.println(mappedByteBuffer.remaining());
//        System.out.println(mappedByteBuffer.limit());
//        mappedByteBuffer.force();

        int len = mappedByteBuffer.getInt();
        byte[] result = new byte[len];
        mappedByteBuffer.get(result);
        System.out.println(new String(result));
        System.out.println(mappedByteBuffer.capacity());
        System.out.println(mappedByteBuffer.position());
        System.out.println(mappedByteBuffer.remaining());
        System.out.println(mappedByteBuffer.limit());
    }
}
