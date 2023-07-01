package com.example.demotest.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class InitialierHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new RequestChannelHandler1());//请求处理器1
        socketChannel.pipeline().addLast(new ResponseChannelHandler1());//响应处理器1
        socketChannel.pipeline().addLast(new RequestChannelHandler2());//请求处理器2
        socketChannel.pipeline().addLast(new ResponseChannelHandler2());//响应处理器2
    }
}
