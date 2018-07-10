package org.andrew.commons.mqoper.rkt.impl;

import org.andrew.commons.exception.context.ContextException;
import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.rkt.abstracts.AbstractMQSimpleContext;
import org.andrew.commons.mqpoer.config.MQContextEnvConfig;
import org.andrew.commons.mqpoer.entitys.ConsumeMessage;
import org.andrew.commons.mqpoer.entitys.ProduceMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:
 * @Date: Created in 2018/7/10 18:39
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public class MQSimpleContext extends AbstractMQSimpleContext {

    private static final Logger logger = LoggerFactory.getLogger(MQSimpleContext.class);


    @Override
    public void initContext(MQContextEnvConfig contextEnvConfig) throws ContextException {

    }

    @Override
    public boolean produce(ProduceMessage produceMessage) throws ProducerException {
        return false;
    }

    @Override
    public ConsumeMessage consume() throws ConsumerException {
        return null;
    }

    @Override
    public void destoryContext() {

    }
}
