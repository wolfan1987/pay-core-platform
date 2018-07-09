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
 * 用户领取卡券事件。
 *
 * @author andrewliu
 */
@Component("userGetCardHandler")
public class UserGetCardHandler implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserGetCardHandler.class);

    @Override
    public void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler, HandlerChain chain) {
        String eventType = recvMsg.getEvent();
        if (WxEventEnum.USER_GET_CARD.equalsName(eventType)) {
            LOGGER.info("获取会员卡信息事件");
        } else {
            chain.handler(recvMsg, wxcpt, handler);
        }
    }
}
