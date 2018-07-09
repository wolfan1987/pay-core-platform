package org.andrew.commons.wechat.event.handler;


import org.andrew.commons.wechat.constants.WxEventEnum;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.handler.Handler;
import org.andrew.commons.wechat.handler.HandlerChain;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.vo.EventHandlerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 从卡券进入公众号会话事件。
 *
 * @author andrewliu
 */
@Component("userEnterFromCardHandler")
public class UserEnterFromCardHandler implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEnterFromCardHandler.class);

    @Override
    public void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler, HandlerChain chain) {
        String eventType = recvMsg.getEvent();
        if (WxEventEnum.USER_ENTER_SESSION_FROM_CARD.equalsName(eventType)) {
            LOGGER.info("从卡券进入公众号会话事件");
        } else {
            chain.handler(recvMsg, wxcpt, handler);
        }
    }
}
