package com.example.demotest.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class MappedByteBufferDemo1 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile(new File("D:\\mappedByteBuffer.txt"),"rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer mappedByteBuffer1 = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
        int OS_PAGE_SIZE = 1024 * 4;
        byte[] b = new byte[]{1,2,3,4,5,6};
        ByteBuffer mappedByteBuffer2 = mappedByteBuffer1.slice();
        mappedByteBuffer2.put(b);
        ByteBuffer mappedByteBuffer = mappedByteBuffer1.slice();
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.get());
        System.out.println(mappedByteBuffer.limit());
        System.out.println(mappedByteBuffer.capacity());
        System.out.println(mappedByteBuffer.position());
//        clean(mappedByteBuffer);
        System.in.read();
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
