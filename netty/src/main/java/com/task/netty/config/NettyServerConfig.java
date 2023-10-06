package com.task.netty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@Configuration
public class NettyServerConfig {
    @Autowired
    private DataHandler dataHandler;

    @Value("8080")
    private int serverPort;

    @Bean
    public ServerBootstrap nettyServerBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                      .channel(NioServerSocketChannel.class)
                      .childHandler(new ChannelInitializer<SocketChannel>() {
                          @Override
                          protected void initChannel(SocketChannel ch) {
                              ChannelPipeline pipeline = ch.pipeline();
                              pipeline.addLast(new StringDecoder(), new StringEncoder());
                              pipeline.addLast(dataHandler);
                          }
                      });
        return serverBootstrap;
    }

    @Bean
    public ChannelFuture nettyServer() {
        return nettyServerBootstrap().bind(serverPort);
    }
}


