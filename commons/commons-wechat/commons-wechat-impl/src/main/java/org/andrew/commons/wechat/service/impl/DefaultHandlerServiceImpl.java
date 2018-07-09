package org.andrew.commons.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;

import org.andrew.commons.wechat.event.handler.main.ProxiedHandler;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvEventMsg;
import org.andrew.commons.wechat.eventmsg.pojo.WxRecvMsg;
import org.andrew.commons.wechat.security.WxBizMsgCrypt;
import org.andrew.commons.wechat.service.EventHandlerService;
import org.andrew.commons.wechat.utils.WechatMsgUtil;
import org.andrew.commons.wechat.vo.EventHandlerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 事件处理入口。
 *
 * @author andrewliu
 */
@Service("eventHandlerService")
public class DefaultHandlerServiceImpl implements EventHandlerService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultHandlerServiceImpl.class);

    @Override
    public void execute(EventHandlerVo eventHandlerVo) {
        logger.info("进入处理微信事件函数");
        WxBizMsgCrypt wxcpt = new WxBizMsgCrypt(eventHandlerVo.getToken(),
                                                eventHandlerVo.getEncodeAesKey(),
                                                eventHandlerVo.getAppid());
        wxcpt.setTimestamp(eventHandlerVo.getTimestamp());// 时间戳，可以自己生成，也可以用URL参数的timestamp
        wxcpt.setNonce(eventHandlerVo.getNonce());// 随机串，可以自己生成，也可以用URL参数的nonce
        String fromXml = eventHandlerVo.getFromXml();
        logger.info("接收到微信推送的报文内容：\r\n{}", fromXml);
        // 将字符串转换为InputStream
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(fromXml.getBytes("UTF-8"));
            WxRecvMsg msg = WechatMsgUtil.recv(in);
            if (msg instanceof WxRecvEventMsg) {
                WxRecvEventMsg recvMsg = (WxRecvEventMsg) msg;
                logger.debug("转换微信推送报文内容对象：{}", JSONObject.toJSONString(recvMsg));
                //把事件类型转为大写
                recvMsg.setEvent(recvMsg.getEvent().toUpperCase());
                //处理事件
                ProxiedHandler proxiedHandler = new ProxiedHandler();
                proxiedHandler.handler(recvMsg, wxcpt, eventHandlerVo);
            }
        } catch (Exception ex) {
            logger.error("微信事件处理出错", ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("关闭输入流出错出错", ex);
            }
        }


    }

}
