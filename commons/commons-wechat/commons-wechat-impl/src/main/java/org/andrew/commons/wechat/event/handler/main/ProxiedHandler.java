package org.andrew.commons.wechat.event.handler.main;


import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.handler.Handler;
import org.andrew.commons.wechat.handler.HandlerChain;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.vo.EventHandlerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 代理handler.加载配置文件wx-handler.xml中所有的类。
 *
 * @author andrewliu
 */
public class ProxiedHandler implements HandlerChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxiedHandler.class);

    private List<Handler> handlerList;

    private int index = 0;

    public ProxiedHandler() {
        handlerList = WxHandlerChainManager.getInstance().getHandlerList();
    }

    @Override
    public void handler(WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler) {
        if (this.handlerList == null || this.handlerList.size() == this.index) {
            LOGGER.info("handler处理链已经到链尾，没有匹配的事件链");
        } else {
            this.handlerList.get(this.index++).handler(recvMsg, wxcpt, handler, this);
        }
    }
}
