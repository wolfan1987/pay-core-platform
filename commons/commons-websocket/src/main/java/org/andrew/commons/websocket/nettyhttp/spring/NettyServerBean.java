package org.andrew.commons.websocket.nettyhttp.spring;


import org.andrew.commons.websocket.nettyhttp.NettyServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * netty服务器(Spring)。
 * Created by andrewliu on 2017/5/17.
 */
public class NettyServerBean implements InitializingBean {

    private Integer port;

    private NettyServer nettyServer;

    private void build() {
        nettyServer = new NettyServer();
        nettyServer.setPort(port);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(port, "port is must");
        build();
    }

    /**
     * 初始化。
     */
    public void init() {
        if (nettyServer != null) {
            nettyServer.init();
        }
    }

    /**
     * 销毁。
     */
    public void destroy() {
        if (nettyServer != null) {
            nettyServer.destroy();
        }
    }

    /**
     * 注入端口。
     *
     * @param port 端口
     */
    public void setPort(Integer port) {
        this.port = port;
    }

}
