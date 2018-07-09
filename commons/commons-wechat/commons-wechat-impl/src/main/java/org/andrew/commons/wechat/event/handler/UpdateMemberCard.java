package org.andrew.commons.wechat.event.handler;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

import org.andrew.commons.wechat.constants.WxEventEnum;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.handler.Handler;
import org.andrew.commons.wechat.handler.HandlerChain;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.vo.EventHandlerVo;
import org.springframework.stereotype.Component;

/**
 * 会员卡内容更新事件。
 * 当用户的会员卡积分余额发生变动时，微信会推送事件告知开发者。
 *
 * @author andrewliu
 */
@Component("updateMemberCard")
public class UpdateMemberCard implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateMemberCard.class);

    @Override
    public void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler, HandlerChain chain) {
        String eventType = recvMsg.getEvent();
        if (WxEventEnum.UPDATE_MEMBER_CARD.equalsName(eventType)) {
            LOGGER.info("会员卡内容更新事件");
        } else {
            chain.handler(recvMsg, wxcpt, handler);
        }
    }
}
