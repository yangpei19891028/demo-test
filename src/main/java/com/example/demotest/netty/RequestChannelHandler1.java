package com.example.demotest.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class RequestChannelHandler1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx , Object msg) throws Exception {
        System.out.println("请求处理器1");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello word" , Charset.forName("gb2312")));
        ctx.channel().writeAndFlush("hello word 1111");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello word1" , Charset.forName("gb2312")));
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello word2" , Charset.forName("gb2312")));

        super.channelRead(ctx,msg);
    }
}