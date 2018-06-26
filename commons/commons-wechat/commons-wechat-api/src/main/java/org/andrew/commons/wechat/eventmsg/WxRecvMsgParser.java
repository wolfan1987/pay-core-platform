package org.andrew.commons.wechat.eventmsg;


import org.andrew.commons.wechat.eventmsg.pojo.WxRecvMsg;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 * 接收消息主体接口。
 */
public interface WxRecvMsgParser {

    WxRecvMsg parser(Document doc) throws JDOMException;
}
