package com.example.demotest.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

public class Test7 {

    public static void main(String[] args) {
        ByteBuf body = Unpooled.buffer(3);
        body.writeShort(Short.valueOf("1"));
        body.writeByte(2);
        body.writeBytes(new byte[0]);
        System.out.println(body.capacity());
        System.out.println(body.readerIndex());
        System.out.println(body.writerIndex());
        System.out.println(body.toString());
    }
}
