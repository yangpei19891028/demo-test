package com.example.demotest.netty.zhonghuashishan;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.SocketAddress;

public class NettyServerHandler extends SimpleChannelInboundHandler {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        byte[] requestBytes = new byte[requestByteBuf.readableBytes()];
        requestByteBuf.readBytes(requestBytes);
        String request = new String(requestBytes);
        System.out.println("接收到请求内容：【" + request + "】");
        //ByteBuf responseByteBuf = Unpooled.copiedBuffer("PooledByteBufAllocator为了减少锁竞争，池是通过threadlocal来实现的。也就是分配的时候会从本线程(这里就是业务线程)的threadlocal里取。而channel.writeAndFlush调用后，在将buffer写到socket后，这个buffer将被回收到池里。回收的时候也是通过thread local找到对应的池，回收掉。这样就有一个问题，分配的时候是在业务线程，也就是说从业务线程的threadlocal对应的池里分配的，而回收的时候是在IO线程。这两个是不同的线程。池的作用完全丧失了，一个线程不断地去分配，不断地转移到另外一个池".getBytes());
        ByteBuf responseByteBuf = Unpooled.copiedBuffer("响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求响应请求".getBytes());
        ctx.writeAndFlush(responseByteBuf);//从当前handler反向出站
//        ctx.channel().close();
//        ctx.channel().writeAndFlush(responseByteBuf);//从pipeline最后一个handler开始出站
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("1111111111111111111" + ctx.channel().eventLoop());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.ALL_IDLE)) {
                closeChannel(ctx.channel());
            }
        }
    }

    public void closeChannel(Channel channel) {
        final String addrRemote = parseChannelRemoteAddr(channel);
        channel.close().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("closeChannel: close the connection to remote address[{"+addrRemote+"}] result: {"+future.isSuccess()+"}");
            }
        });
    }

    public static String parseChannelRemoteAddr(final Channel channel) {
        if (null == channel) {
            return "";
        }
        SocketAddress remote = channel.remoteAddress();
        final String addr = remote != null ? remote.toString() : "";

        if (addr.length() > 0) {
            int index = addr.lastIndexOf("/");
            if (index >= 0) {
                return addr.substring(index + 1);
            }

            return addr;
        }

        return "";
    }
}
