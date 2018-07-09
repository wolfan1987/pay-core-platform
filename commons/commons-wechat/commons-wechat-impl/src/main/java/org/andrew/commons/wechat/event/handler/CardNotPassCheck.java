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
 * 卡券审核不通过事件,生成的卡券通过审核时推送。
 *
 * @author andrewliu
 */
@Component("cardNotPassCheck")
public class CardNotPassCheck implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardNotPassCheck.class);

    @Override
    public void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler, HandlerChain chain) {
        String eventType = recvMsg.getEvent();
        if (WxEventEnum.CARD_NOT_PASS_CHECK.equalsName(eventType)) {
            LOGGER.info("卡券未通过审核事件");
        } else {
            chain.handler(recvMsg, wxcpt, handler);
        }
    }
}
