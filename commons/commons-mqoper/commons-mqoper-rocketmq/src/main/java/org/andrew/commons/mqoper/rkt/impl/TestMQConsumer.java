package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractConsumer;
import org.andrew.commons.mqoper.rkt.model.TestConsumeMessage;
import org.andrew.commons.mqpoer.config.ConsumeConfig;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;


/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  测试用Conusmer实现
 * @Date: Created in 2018/7/10 16:52
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class TestMQConsumer  extends AbstractConsumer<TestConsumeMessage,ConsumeConfig> {

    @Override
    public void processor(TestConsumeMessage consumeMessage) throws ConsumerException {

    }

    @Override
    public boolean preProcessor(TestConsumeMessage consumeMessage) throws ConsumerException {
        return false;
    }

    @Override
    public boolean endProcessor(TestConsumeMessage consumeMessage) throws ConsumerException {
        return false;
    }


    @Override
    public void setConfig(ConsumeConfig config) {
        this.config = config;
    }
}
