package org.andrew.commons.wechat.eventmsg;


import org.andrew.commons.wechat.eventmsg.pojo.WxRecvMsg;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 * 接收消息主体处理。
 */
public abstract class WxRecvMsgBaseParser implements WxRecvMsgParser {

    /**
     * 文档装好成微信消息体。
     *
     * @param doc 接收到的文档。
     * @return 返回微信接收到的消息体
     */
    public WxRecvMsg parser(Document doc) throws JDOMException {
        Element root = doc.getRootElement();
        String toUserName = getElementText(root, "ToUserName");
        String fromUserName = getElementText(root, "FromUserName");
        String createTime = getElementText(root, "CreateTime");
        String msgType = getElementText(root, "MsgType");
        String msgId = getElementText(root, "MsgId");
        return parser(root, new WxRecvMsg(toUserName, fromUserName, createTime, msgType, msgId));
    }

    protected abstract WxRecvMsg parser(Element root, WxRecvMsg msg) throws JDOMException;

    protected String getElementText(Element elem, String xpath) throws JDOMException {
        return elem.getChildText(xpath);
    }

}
