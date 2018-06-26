package org.andrew.commons.wechat.eventmsg.text;


import org.andrew.commons.wechat.eventmsg.WxRecvMsgBaseParser;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvMsg;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvTextMsg;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 * 接收文本消息处理。
 */
public class WxRecvTextMsgParser extends WxRecvMsgBaseParser {

    @Override
    protected WxRecvTextMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
        return new WxRecvTextMsg(msg, getElementText(root, "Content"));
    }
}
