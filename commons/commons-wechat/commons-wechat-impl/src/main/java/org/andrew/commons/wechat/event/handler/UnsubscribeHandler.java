package org.andrew.commons.wechat.event.handler;


import org.andrew.commons.wechat.constants.WxEventEnum;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.handler.Handler;
import org.andrew.commons.wechat.handler.HandlerChain;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.vo.EventHandlerVo;
import org.springframework.stereotype.Component;

/**
 * 取消关注事件处理。
 *
 * @author andrewliu
 */
@Component("unsubscribeHandler")
public class UnsubscribeHandler implements Handler {

    @Override
    public void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler, HandlerChain chain) {
        String eventType = recvMsg.getEvent();
        if (WxEventEnum.UNSUBSCRIBE.equalsName(eventType)) {
            System.out.println("取消关注事件处理");
        } else {
            chain.handler(recvMsg, wxcpt, handler);
        }
    }
}
