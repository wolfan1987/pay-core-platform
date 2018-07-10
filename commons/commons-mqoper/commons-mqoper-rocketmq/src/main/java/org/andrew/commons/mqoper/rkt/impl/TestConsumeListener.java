package org.andrew.commons.mqoper.rkt.impl;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  测试用消费监听实现
 * @Date: Created in 2018/7/10 17:36
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class TestConsumeListener  implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(TestConsumeListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        return null;
    }
}
