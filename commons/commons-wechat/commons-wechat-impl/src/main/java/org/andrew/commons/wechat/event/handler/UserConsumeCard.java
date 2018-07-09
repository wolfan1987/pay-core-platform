package org.andrew.commons.wechat.handler;


import org.andrew.commons.wechat.constants.WxEventEnum;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.vo.EventHandlerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 微信卡券核销事件。
 *
 * @author andrewliu
 */
@Component("userConsumeCard")
public class UserConsumeCard implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumeCard.class);

    @Override
    public void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler, HandlerChain chain) {
        String eventType = recvMsg.getEvent();
        if (WxEventEnum.USER_CONSUME_CARD.equalsName(eventType)) {
            LOGGER.info("核销事件推送");
        } else {
            chain.handler(recvMsg, wxcpt, handler);
        }
    }
}
