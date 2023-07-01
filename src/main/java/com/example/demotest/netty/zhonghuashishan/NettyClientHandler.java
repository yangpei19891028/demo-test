package com.example.demotest.netty.zhonghuashishan;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class NettyClientHandler extends ChannelDuplexHandler {

//    private ByteBuf requestByteBuf;
//
//    public NettyClientHandler(){
//        String request = "1";
//        byte[] requestBytes = request.getBytes();
//        requestByteBuf = Unpooled.directBuffer(requestBytes.length);
//        requestByteBuf.writeBytes(requestBytes);
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //ctx.writeAndFlush(requestByteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf responseByteBuf = (ByteBuf) msg;
        byte[] responseBytes = new byte[responseByteBuf.readableBytes()];
        responseByteBuf.readBytes(responseBytes);
        String response = new String(responseBytes);
        System.out.println("收到响应：【" + response + "】");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
