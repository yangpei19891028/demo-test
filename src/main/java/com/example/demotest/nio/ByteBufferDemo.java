package com.example.demotest.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferDemo {
    public static void main(String[] args) throws Exception{
//        byte[] bytes = new byte[]{1,2,3,4,5,6,7,8,9,10};
//        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
//        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(10);
//        System.out.println(byteBuffer1.capacity());
//        System.out.println(byteBuffer1.limit());
//        byteBuffer1.put(byteBuffer);
////        System.out.println(byteBuffer.capacity());
////        System.out.println(byteBuffer.position());
////        System.out.println(byteBuffer.limit());
//        //System.out.println(byteBuffer.get());
////        byteBuffer.get();
////        byteBuffer.get();
////        System.out.println(byteBuffer.remaining());
////        byteBuffer.get();
////        byteBuffer.get();
////        byteBuffer.get();
////        byteBuffer.get();
////        System.out.println(byteBuffer.capacity());
////        System.out.println(byteBuffer.position());
////        System.out.println(byteBuffer.limit());
////        System.out.println(byteBuffer.remaining());
//        byteBuffer.position(3);
//        System.out.println(byteBuffer.get());
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.remaining());
//        System.out.println("---------------------------");
//        byteBuffer.mark();
//        byteBuffer.get();
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.remaining());
//        System.out.println("----------------------------");
//        byteBuffer.reset();
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.remaining());
//        System.out.println("---------------------------");
//        byteBuffer.put((byte)7);
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.remaining());
//        System.out.println(byteBuffer.array()[0]+""+byteBuffer.array()[1]+""+byteBuffer.array()[2]+""+byteBuffer.array()[3]
//                +"" +byteBuffer.array()[4]+""+byteBuffer.array()[5]+"");
//        System.out.println("--------------------");
//        byteBuffer.flip();
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.remaining());
//
//        System.out.println("---------------------");
//        byte[] dst = new byte[5];
//        byteBuffer.get(dst,0,5);
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.remaining());
//        System.out.println("-------------------------");
//        byteBuffer.clear();
//        System.out.println(byteBuffer.get());

//          test1();
        ByteBufferDemo byteBufferDemo = new ByteBufferDemo();
        byteBufferDemo.test5();
    }

    public static void test1(){
        byte[] bytes = new byte[]{1,2,3,4,5,6,7,8,9,10};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        ByteBuffer b1 = byteBuffer.slice();
        System.out.println("b1:" + b1.position());
        System.out.println("b1:" + b1.limit());
        System.out.println("b1:" + b1.capacity());
        byteBuffer.put((byte)11);
        ByteBuffer duplicate = byteBuffer.duplicate();
        System.out.println("----------");
        System.out.println("duplicate:" + duplicate.position());
        System.out.println("duplicate:" + duplicate.limit());
        System.out.println("duplicate:" + duplicate.capacity());
        System.out.println("----------");
        System.out.println("b1:" + b1.position());
        System.out.println("b1:" + b1.limit());
        System.out.println("b1:" + b1.capacity());
        System.out.println("b1:" + b1.get());
        System.out.println("----------");
        System.out.println("byteBuffer:" + byteBuffer.position());
        System.out.println("byteBuffer:" + byteBuffer.limit());
        System.out.println("byteBuffer:" + byteBuffer.capacity());
        byteBuffer.flip();
        System.out.println("byteBuffer:" + byteBuffer.get());
        b1.position(3);
        ByteBuffer b2 = b1.slice();
        System.out.println("b2:" + b2.position());
        System.out.println("b2:" + b2.limit());
        System.out.println("b2:" + b2.capacity());
        b2.limit(6);
        System.out.println("b2_1:" + b2.position());
        System.out.println("b2_1:" + b2.limit());
        System.out.println("b2_1:" + b2.capacity());
    }

    public void test2(){
        ByteBuffer byteBuf = ByteBuffer.allocate(12);
        //byteBuf.order(ByteOrder.BIG_ENDIAN);
        byteBuf.put((byte)this.channelId);
        byteBuf.putShort((short)this.shootingCmd);
        byteBuf.putShort((short)this.interval);
        byteBuf.put((byte)this.saveFlag);
        byteBuf.put((byte)this.resolution);
        byteBuf.put((byte)this.quality);
        byteBuf.put((byte)this.brightness);
        byteBuf.put((byte)this.contrastRatio);
        byteBuf.put((byte)this.saturation);
        byteBuf.put((byte)this.chroma);
        System.out.println(byteBuf.order().toString());
        System.out.println(byteBuf.array()[0]);
        byteBuf.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println(byteBuf.array()[0]);
    }

    private int channelId = 101;
    private int shootingCmd = 1;
    private int interval = 1;
    private int saveFlag = 0;
    private int resolution = 4;
    private int quality = 1;
    private int brightness = 35;
    private int contrastRatio = 25;
    private int saturation = 31;
    private int chroma = 31;

    public void test3(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        byte[] b = {1,2,3,4,5,6,7,8};
        byteBuffer.put(b);
        int position = byteBuffer.position();
        ByteBuffer slice = byteBuffer.slice();
        System.out.println(slice.position());
        System.out.println(slice.limit());
        System.out.println(slice.capacity());
        slice.position(position);
    }

    public void test4(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        ByteBuffer slice = byteBuffer.slice();
        byte[] b = {1,2,3,4,5,6,7,8};
        slice.put(b);
        System.out.println(slice.position());
        System.out.println(slice.limit());
        System.out.println(slice.capacity());
        System.out.println("------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }

    public void test5(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        byteBuffer.put((byte)0);
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)2);
        byteBuffer.put((byte)3);
        byteBuffer.put((byte)4);
        System.out.println(byteBuffer.position() + "------------" + byteBuffer.limit() + "-------------" + byteBuffer.capacity());
        byteBuffer.flip();
        System.out.println(byteBuffer.position() + "------------" + byteBuffer.limit() + "-------------" + byteBuffer.capacity());
        byteBuffer.get();
        System.out.println(byteBuffer.position() + "------------" + byteBuffer.limit() + "-------------" + byteBuffer.capacity());
        byteBuffer.compact();
        System.out.println(byteBuffer.position() + "------------" + byteBuffer.limit() + "-------------" + byteBuffer.capacity());
        byteBuffer.flip();
        System.out.println(byteBuffer.position() + "------------" + byteBuffer.limit() + "-------------" + byteBuffer.capacity());
        System.out.println(byteBuffer.get());
    }
}
