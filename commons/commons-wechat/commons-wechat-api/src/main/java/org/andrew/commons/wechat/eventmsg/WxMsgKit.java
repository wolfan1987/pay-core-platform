package org.andrew.commons.wechat.eventmsg;


import org.andrew.commons.wechat.eventmsg.pojo.WxRecvMsg;
import org.andrew.commons.wechat.eventmsg.pojo.WxSendMsg;
import org.andrew.commons.wechat.eventmsg.text.WxRecvTextMsgParser;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信消息类型。
 */
public final class WxMsgKit {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxMsgKit.class);

    private static final Map<String, WxRecvMsgParser> recvParserMap = new HashMap<>();

    static {
        // 文本消息解析程序
        recvParserMap.put("text", new WxRecvTextMsgParser());
        //事件消息解析程序
        recvParserMap.put("event", new WxRecvEventMsgParser());
    }

    /**
     * 解析微信传过来的xml报文。
     *
     * @param in 输入流
     * @return WxRecvMsg
     * @throws JDOMException JDOMException
     * @throws IOException   IOException
     */
    public static WxRecvMsg parse(InputStream in) throws JDOMException, IOException {
        //读取输入流
        Document dom = new SAXBuilder().build(in);
        Element msgType = dom.getRootElement().getChild("MsgType");
        if (null != msgType) {
            //把字符中的所有字母全部转换为小写
            String txt = msgType.getText().toLowerCase();
            LOGGER.info("text:{}", txt);
            WxRecvMsgParser parser = recvParserMap.get(txt);
            LOGGER.info("parser:{}", parser);
            if (null != parser) {
                return parser.parser(dom);
            } else {
                LOGGER.info(txt);
            }
        }
        return null;
    }

    public static Document parse(WxSendMsg msg) throws JDOMException {
        return msg.toDocument();
    }
}
