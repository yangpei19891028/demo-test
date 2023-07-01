package com.example.demotest.nio;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;

public class MappedByteBufferDemo {

    public static void main(String[] args) throws Exception {
        //MappedByteBuffer 便是MMAP的操作类(获得一个 1.5G 的文件)
        long start = System.currentTimeMillis();
        //D:\mappedByteBuffer.txt
        RandomAccessFile file = new RandomAccessFile("D:\\mappedByteBuffer.txt","rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 512 * 1024 * 1024);
// write
        int OS_PAGE_SIZE = 1024 * 4;
        byte[] b = new byte[]{1,2,3,4,5,6};
        mappedByteBuffer.clear();
        MappedByteBuffer m1 = (MappedByteBuffer) mappedByteBuffer.slice();
        m1.putInt(b.length);
        m1.put(b);
        mappedByteBuffer.force();
        System.out.println(m1.position());
        System.out.println(m1.capacity());
        System.out.println(m1.limit());
        System.out.println("---------------------");
        System.out.println(m1.order().toString());
        ByteBuffer compact = mappedByteBuffer.slice();
        System.out.println(compact.position());
        System.out.println(compact.capacity());
        System.out.println(compact.limit());
        int length = compact.getInt();
        System.out.println(length);
        byte[] bytes = new byte[length];
        System.out.println(compact.get(bytes));
        System.out.println(Arrays.toString(bytes));
        channel.close();
        clean(mappedByteBuffer);
//
//        length = compact.getInt();
//        System.out.println(length);
//        bytes = new byte[length];
//        System.out.println(compact.get(bytes));
//        System.out.println(Arrays.toString(bytes));

//        System.out.println("---------------------");
//        ByteBuffer duplicate = mappedByteBuffer.duplicate();
//        duplicate.position(0);
//        System.out.println(duplicate.position());
//        System.out.println(duplicate.capacity());
//        System.out.println(duplicate.limit());
//        int length = duplicate.getInt();
//        System.out.println(length);
//        byte[] bytes = new byte[length];
//        System.out.println(duplicate.get(bytes));
//        System.out.println(Arrays.toString(bytes));
//
//        length = duplicate.getInt();
//        System.out.println(length);
//        bytes = new byte[length];
//        System.out.println(duplicate.get(bytes));
//        System.out.println(Arrays.toString(bytes));
//
//        System.out.println("@@@@@@@@@@@@@@@@@@");
//        System.out.println(duplicate.position());
//        System.out.println(duplicate.capacity());
//        System.out.println(duplicate.limit());
//        mappedByteBuffer.putInt(b.length);
//        mappedByteBuffer.put(b);
//
//        System.out.println("###########################");
//        System.out.println(mappedByteBuffer.position());
//        System.out.println(mappedByteBuffer.capacity());
//        System.out.println(mappedByteBuffer.limit());
//
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        while(duplicate.hasRemaining()){
//            length = duplicate.getInt();
//            if(length == 0){
//                break;
//            }
//            System.out.println(length);
//            bytes = new byte[length];
//            System.out.println(duplicate.get(bytes));
//            System.out.println(Arrays.toString(bytes));
//        }
//        int length = compact.getInt();
//        System.out.println(length);
//        byte[] bytes = new byte[length];
//        System.out.println(compact.get(bytes));
//        System.out.println(Arrays.toString(bytes));
//        mappedByteBuffer.flip();
//        System.out.println(mappedByteBuffer.getInt());
//        System.out.println(mappedByteBuffer.get());
//        System.out.println(mappedByteBuffer.get());
//        System.out.println(mappedByteBuffer.get());
//        System.out.println(mappedByteBuffer.get());
//        System.out.println(mappedByteBuffer.get());
//        System.out.println(mappedByteBuffer.get());
//        ByteBuffer byteBuffer = mappedByteBuffer.slice();
//        int flush = 0;
//        for (int i = 0, j = 0; i < 512 * 1024 * 1024; i += OS_PAGE_SIZE, j++) {
//            byteBuffer.put(i, (byte) 0);
//            // force flush when flush disk type is sync
//            if ((i / OS_PAGE_SIZE) - (flush / OS_PAGE_SIZE) >= 1024 / 4 * 16) {
//                flush = i;
//                mappedByteBuffer.force();
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("cost time : " + (end - start));

//从当前 mmap 指针的位置写入 4b 的数据
//        mappedByteBuffer.put(data);
//指定 position 写入 4b 的数据
//        MappedByteBuffer subBuffer = (MappedByteBuffer) mappedByteBuffer.slice();
//        subBuffer.position(position);
//        subBuffer.put(data);
//        System.out.println(mappedByteBuffer.limit());
//        System.out.println(mappedByteBuffer.capacity());
//        System.out.println(mappedByteBuffer.position());
////        clean(mappedByteBuffer);
//        RandomAccessFile file1 = new RandomAccessFile("D:\\mappedByteBuffer.txt","rw");
//        FileChannel channel1 = file1.getChannel();
//        MappedByteBuffer mappedByteBuffer1 = channel1.map(FileChannel.MapMode.READ_WRITE, 0, 200 * 1024 * 1024);
//        mappedByteBuffer1.put(b);
//
//// read
//        byte[] data = new byte[4];
//        int position = 8;
////从当前 mmap 指针的位置读取 4b 的数据
//        mappedByteBuffer.get(data)；
////指定 position 读取 4b 的数据
//        MappedByteBuffer subBuffer = mappedByteBuffer.slice();
//        subBuffer.position(position);
//        subBuffer.get(data);
    }

    public static void clean(final ByteBuffer buffer) {
        if (buffer == null || !buffer.isDirect() || buffer.capacity() == 0)
            return;
        invoke(invoke(viewed(buffer), "cleaner"), "clean");
    }

    private static Object invoke(final Object target, final String methodName, final Class<?>... args) {
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    Method method = method(target, methodName, args);
                    method.setAccessible(true);
                    return method.invoke(target);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
    }

    private static ByteBuffer viewed(ByteBuffer buffer) {
        String methodName = "viewedBuffer";
        Method[] methods = buffer.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("attachment")) {
                methodName = "attachment";
                break;
            }
        }

        ByteBuffer viewedBuffer = (ByteBuffer) invoke(buffer, methodName);
        if (viewedBuffer == null)
            return buffer;
        else
            return viewed(viewedBuffer);
    }

    private static Method method(Object target, String methodName, Class<?>[] args)
            throws NoSuchMethodException {
        try {
            return target.getClass().getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            return target.getClass().getDeclaredMethod(methodName, args);
        }
    }
}
