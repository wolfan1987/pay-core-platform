package org.andrew.commons.wechat.utils;


import org.andrew.commons.wechat.eventmsg.WxMsgKit;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvMsg;
import org.jdom.JDOMException;

import java.io.IOException;
import java.io.InputStream;

/**
 * 微信消息工具类。
 *
 * @author andrewliu
 */
public final class WechatMsgUtil {

    public static WxRecvMsg recv(InputStream in) throws JDOMException, IOException {
        return WxMsgKit.parse(in);
    }

}
