package org.andrew.commons.websocket.nettyhttp;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NettyChannel工具类。
 * Created by andrewliu on 2017/5/17.
 */
public class NettyChannelUtil {
    private static final Logger logger = LoggerFactory.getLogger(NettyChannelUtil.class);

    /**
     * 推送String数据到前端。
     *
     * @param key 唯一key值
     * @param msg 需要推送消息
     * @return 是否已经推送
     */
    public static boolean push(String key, Object msg) {
        logger.debug("key:{}", key);
        logger.info("msg:{}", msg);
        Channel channel = NettyChannelCenter.get(key);
        if (channel != null) {
            String text;
            if (msg instanceof String) {
                text = (String) msg;
            } else {
                text = JSON.toJSONString(msg);
            }
            TextWebSocketFrame tws = new TextWebSocketFrame(text);
            channel.writeAndFlush(tws);
            return true;
        }
        return false;
    }

}
