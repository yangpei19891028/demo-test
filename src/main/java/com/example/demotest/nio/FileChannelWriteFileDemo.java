package com.example.demotest.nio;



import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteFileDemo {
    public static void main(String[] args) throws Exception {
        //顺序写，多次写追加
        FileOutputStream out = new FileOutputStream("D:\\fileChannel.txt");
        FileChannel fileChannel = out.getChannel();//线程安全
        ByteBuffer byteBuffer = ByteBuffer.wrap("123".getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        fileChannel.write(byteBuffer);
        byteBuffer.rewind();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        fileChannel.write(byteBuffer);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("123".getBytes());
        fileChannel.write(byteBuffer1);

        //随机写
        System.out.println(fileChannel.position());
        fileChannel.position(3);//这种的会修改channel的position,达到的是覆盖的效果
        ByteBuffer append = ByteBuffer.wrap("ts".getBytes());
        fileChannel.write(append);
        fileChannel.close();
        out.close();
    }
}
