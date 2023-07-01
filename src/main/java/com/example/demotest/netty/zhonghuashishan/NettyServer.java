package com.example.demotest.netty.zhonghuashishan;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup parentGroup  = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            DefaultEventExecutorGroup defaultEventExecutorGroup = new DefaultEventExecutorGroup(
                    2,
                    new ThreadFactory() {

                        private AtomicInteger threadIndex = new AtomicInteger(0);

                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, "NettyServerCodecThread_" + this.threadIndex.incrementAndGet());
                        }
                    });
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //ch.pipeline().addLast(new IdleStateHandler(5,5,5));
                            ch.pipeline().addLast(defaultEventExecutorGroup,new NettyServerHandler());
                        }
                    });
//            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
//            channelFuture.channel().closeFuture().sync();
            ChannelFuture channelFuture = serverBootstrap.bind(9000);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println(future.channel());
                    }else{
                        channelFuture.channel().close();
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
