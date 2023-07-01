package com.example.demotest.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class BitDemo {

    public static void main(String[] args) throws IOException {
        String msg = "33350||1642910834000||0||786435||120031181||31731269||6||0||0||27||12||1786225||321324198509093617||99||5006272||5007308||5000010||5000000||4||苏D77588||2||0||0||0||1642910835821||province808||040038658911||2056036";
        byte[] bytes = msg.getBytes();
        int len = bytes.length;
        int requestType = 1;
        //0000 0000 0000 0000 0000 0000 0000 0000
        int originalLen = (len >> 16 & 0xFF) << 16 | (len >> 8 & 0xFF) << 8 | (len & 0xFF) | (requestType & 0xFF) << 24;
        System.out.println(len);
//        RandomAccessFile file = new RandomAccessFile("D:\\bitDemo.txt","rw");
//        FileChannel channel = file.getChannel();
//        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 10 * 1024 * 1024);
//        mappedByteBuffer.putInt(originalLen);
//        mappedByteBuffer.put(bytes);

//        int originalLen1 = mappedByteBuffer.getInt();
        int originalLen1 = originalLen;
        int requestType1 = originalLen1 >> 24 & 0xFF;
        int len1 = originalLen1 & 0xFFFFFF;
        System.out.println(requestType1);
        System.out.println(len1);
    }
}
