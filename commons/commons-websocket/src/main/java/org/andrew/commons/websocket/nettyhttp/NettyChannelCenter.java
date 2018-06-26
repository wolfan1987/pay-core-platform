package org.andrew.commons.websocket.nettyhttp;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Channel内存对象操作。
 *
 * @author andrewliu
 */
public class NettyChannelCenter {

    private static final Logger               logger      = LoggerFactory.getLogger(
        NettyChannelCenter.class);
    // 内存Channel对象。
    private static       Map<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();

    /**
     * 将Channel放入内存中，如果是覆盖则需要将原Channel关闭。
     *
     * @param key     唯一key值
     * @param channel channel对象
     */
    public static void put(String key, Channel channel) {
        logger.debug("put通道：{}", key);
        Channel oldChannel = CHANNEL_MAP.get(key);
        //覆盖原来的channel时需要将原channel关闭
        if (oldChannel != null && oldChannel.compareTo(channel) != 0) {
            oldChannel.close();
        }
        CHANNEL_MAP.put(key, channel);
        logger.info("put通道成功");
    }

    /**
     * 移除Channel内存对象。
     *
     * @param channel 对象
     */
    public static void remove(Channel channel) {
        //logger.debug("移除通道channeId：{}", channel..asLongText());
        for (Map.Entry<String, Channel> entry : CHANNEL_MAP.entrySet()) {
            if (entry.getValue().compareTo(channel) == 0) {
                channel.close();
                CHANNEL_MAP.remove(entry.getKey());
                logger.info("移除成功");
                break;
            }
        }
    }

    /**
     * 移除Channel内存对象。
     *
     * @param key 唯一key值
     */
    public static void remove(String key) {
        logger.debug("移除通道key：{}", key);
        if (CHANNEL_MAP.containsKey(key)) {
            Channel channel = CHANNEL_MAP.get(key);
            if (channel != null) {
                channel.close();
            }
            logger.info("移除成功");
            CHANNEL_MAP.remove(key);
        }
    }

    /**
     * 获取活动的Channel内存对象。
     *
     * @param key 唯一key值
     * @return Channel内存对象
     */
    public static Channel get(String key) {
        Channel channel = CHANNEL_MAP.get(key);
        logger.debug("获取通道key：{}", key);
        if (channel != null) {
           // logger.info("已获取通道channelId：{}", channel.id().asLongText());
            logger.info("通道活动状态isActive：{}", channel.isActive());
            return channel;
        }
        return null;
    }
}