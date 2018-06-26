package org.andrew.commons.web.servlet;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.remoting.transport.netty.NettyClient;
import org.jboss.netty.channel.ChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Field;

/**
 * 容器监听器。
 * 处理Dubbo关闭事情。
 * Created by leaves chen[leaves615@gmail.com] on 2017/3/16.
 *
 * @Author leaves chen[leaves615@gmail.com]
 */
public class DubboShutDownListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(DubboShutDownListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("destory dubbo...");
        ProtocolConfig.destroyAll();
        //用反射释放NettyClient所占用的资源, 以避免不能优雅shutdown的问题
        releaseNettyClientExternalResources();
    }


    /**
     * 释放nettyClient 资源。
     */
    private void releaseNettyClientExternalResources() {
        try {
            Field field = NettyClient.class.getDeclaredField("channelFactory");
            field.setAccessible(true);
            ChannelFactory channelFactory = (ChannelFactory) field.get(NettyClient.class);
            channelFactory.releaseExternalResources();
            field.setAccessible(false);
            logger.info("Release NettyClient's external resources");
        } catch (Exception exception) {
            logger.error("Release NettyClient's external resources error", exception);
        }
    }
}
