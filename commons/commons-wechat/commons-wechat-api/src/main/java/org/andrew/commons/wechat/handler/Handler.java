package org.andrew.commons.wechat.handler;


import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.vo.EventHandlerVo;

/**
 * 微信事件处理。
 *
 * @author andrewliu
 */
public interface Handler {

    void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handlerVo,
            HandlerChain handlerChain);
}
