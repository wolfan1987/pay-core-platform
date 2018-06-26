package org.andrew.commons.websocket.nettyhttp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty WebSocket Server。
 * create by andrewliu on 2017/05/17.
 */
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private Integer        port;
    private Channel        channel;

    /**
     * 启动netty WebSocket服务。
     */
    public void init() {
        new Thread(() -> {
            try {
                bossGroup = new NioEventLoopGroup();
                workGroup = new NioEventLoopGroup();
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, workGroup);
                serverBootstrap.channel(NioServerSocketChannel.class);
                serverBootstrap.childHandler(new ChildChannelHandler());
                logger.info("netty WebSocket Server init success, port is {}", port);
                channel = serverBootstrap.bind(port).sync().channel();
                channel.closeFuture().sync();
            } catch (Exception exception) {
                logger.error("netty WebSocket Server init exception:" + exception);
            } finally {
                bossGroup.shutdownGracefully();
                workGroup.shutdownGracefully();
            }
        }).start();
    }

    /**
     * 关闭netty WebSocket服务资源。
     */
    public void destroy() {
        channel.close();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        logger.info("netty WebSocket Server destroy success.");
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}