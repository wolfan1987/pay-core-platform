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
 * 扫描事件,扫码时推送。
 *
 * @author andrewliu
 */
@Component("scanHandler")
public class ScanHandler implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanHandler.class);

    @Override
    public void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handler, HandlerChain chain) {
        String eventType = recvMsg.getEvent();
        if (WxEventEnum.SCAN.equalsName(eventType)) {
            LOGGER.info("扫描事件处理");
        } else {
            chain.handler(recvMsg, wxcpt, handler);
        }
    }
}
