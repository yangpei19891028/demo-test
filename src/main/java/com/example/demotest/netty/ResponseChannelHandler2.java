package com.example.demotest.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.Charset;

public class ResponseChannelHandler2 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx , Object msg , ChannelPromise promise) throws Exception {
        System.out.println("响应处理器2");
        ByteBuf byteMsg = (ByteBuf) msg;
        byteMsg.writeBytes("增加请求2的内容".getBytes(Charset.forName("gb2312")));
        super.write(ctx,msg,promise);
    }
}
