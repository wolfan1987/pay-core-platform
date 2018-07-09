package org.andrew.commons.wechat.event.handler.main;


import org.andrew.commons.wechat.handler.Handler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 注册微信事件处理链。
 *
 * @author andrewliu
 */
public class WxHandlerChainManager implements ApplicationContextAware, InitializingBean {

    private static List<Handler> handlerList;

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Handler> beansOfType = ctx.getBeansOfType(Handler.class);
        Collection<Handler> list = beansOfType.values();
        handlerList = new ArrayList<>(list);
    }

    //懒加载，内部类实例化时,读取配置文件并装载为handlerList
    static class WxHandlerChainManagerHolder {
        private static final WxHandlerChainManager wxHandlerReader = new WxHandlerChainManager();
    }

    public List<Handler> getHandlerList() {
        return handlerList;
    }

    public static WxHandlerChainManager getInstance() {
        return WxHandlerChainManagerHolder.wxHandlerReader;
    }


}
