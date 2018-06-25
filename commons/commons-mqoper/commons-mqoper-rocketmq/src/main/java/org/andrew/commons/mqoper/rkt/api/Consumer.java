package org.andrew.commons.mqoper.rkt.api;


import org.andrew.commons.mqoper.rkt.executes.consumer.ConsumerMessage;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * consumer接口。
 *
 * @author andrewliu
 */
public interface Consumer {

    void doStart() throws MQClientException;

    void doShutdown();

    /**
     * 发布订阅模式只处理单条消息。
     *
     * @param message 消息
     * @return 处理结果 true成功 false失败
     */
    boolean execute(ConsumerMessage message);
}
