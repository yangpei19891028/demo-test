package com.example.demotest.nio;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadFileDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("D:\\fileChannel.txt");
        FileChannel fileChannel = inputStream.getChannel();
        //fileChannel.lock(0,Integer.MAX_VALUE,true);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(9);
        fileChannel.read(byteBuffer);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        byteBuffer.flip();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        byte[] bytes = new byte[9];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
//        while(byteBuffer.hasRemaining()){
//            System.out.print(byteBuffer.get());
//        }
        fileChannel.close();
        inputStream.close();
    }
}
