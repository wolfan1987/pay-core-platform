package org.andrew.commons.wechat.handler;


import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.vo.EventHandlerVo;

/**
 * 处理微信事件。
 *
 * @author andrewliu
 */
public interface HandlerChain {

    void handler(
            WxRecvEventMsg recvMsg, WxBizMsgCrypt wxcpt, EventHandlerVo handlerVo);
}
