package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractProducer;
import org.andrew.commons.mqoper.rkt.model.TestProduceMessage;
import org.andrew.commons.mqoper.config.ProduceConfig;
import org.andrew.commons.mqoper.entitys.ProduceResult;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 18:55
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class TestMQProducer  extends AbstractProducer<TestProduceMessage,ProduceConfig,ProduceResult>{

    @Override
    public ProduceResult doSend(TestProduceMessage produceMessage) throws ProducerException {
        if(preProcessor(produceMessage)){

        }
        return null;
    }

    @Override
    public boolean preProcessor(TestProduceMessage produceMessage) throws ProducerException {
        return true;
    }

    @Override
    public boolean endProcessor(TestProduceMessage produceMessage) throws ProducerException {
        return true;
    }

    @Override
    public void setConfig(ProduceConfig config) {
        this.config = config;
    }

}
