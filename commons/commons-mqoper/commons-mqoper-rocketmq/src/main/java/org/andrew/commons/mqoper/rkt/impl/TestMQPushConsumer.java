package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractPushConsumer;
import org.andrew.commons.mqoper.rkt.model.TestConsumeMessage;
import org.andrew.commons.mqoper.config.ConsumeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  测试用Conusmer实现
 * @Date: Created in 2018/7/10 16:52
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class TestMQPushConsumer extends AbstractPushConsumer<TestConsumeMessage,ConsumeConfig> {

    private static final Logger logger = LoggerFactory.getLogger(TestMQPushConsumer.class);

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

    public void setConfig(ConsumeConfig config) {
        this.config = config;
    }

    @Override
    public ConsumeConfig getConfig() {
        return this.config;
    }
}
