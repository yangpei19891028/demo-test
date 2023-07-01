package com.example.demotest.netty.zhonghuashishan;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(9000)).sync();
            ByteBuf requestByteBuf;
            String request = "发送请求啊啊啊啊啊啊啊啊啊啊啊啊啊";
            byte[] requestBytes = request.getBytes();
            requestByteBuf = Unpooled.directBuffer(requestBytes.length);
            requestByteBuf.writeBytes(requestBytes);
            channelFuture.channel().writeAndFlush(requestByteBuf);
//            channelFuture.channel().eventLoop().scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    ByteBuf requestByteBuf;
//                    String request = "发送请求啊啊啊啊啊啊啊啊啊啊啊啊啊";
//                    byte[] requestBytes = request.getBytes();
//                    requestByteBuf = Unpooled.directBuffer(requestBytes.length);
//                    requestByteBuf.writeBytes(requestBytes);
//                    channelFuture.channel().writeAndFlush(requestByteBuf);
//                }
//            }, 10, 1000, TimeUnit.MILLISECONDS);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }
}
