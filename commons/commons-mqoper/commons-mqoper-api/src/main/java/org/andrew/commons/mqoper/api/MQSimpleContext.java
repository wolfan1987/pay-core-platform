package org.andrew.commons.mqoper.api;

import org.andrew.commons.exception.context.ContextException;
import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.entitys.ConsumeMessage;
import org.andrew.commons.mqoper.config.MQContextEnvConfig;
import org.andrew.commons.mqoper.entitys.ProduceMessage;
import org.andrew.commons.mqoper.entitys.ProduceResult;

/**
 * @author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  消息处理基本上下文对象,用于封装与mq进行通信、轮询调度、持久化相关处理
 * @Date: Created in 2018/7/7 10:49
 * @since  0.0.1
 * @version  0.0.1
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface MQSimpleContext{

    /**
     * 初始化消息处理上下文环境
     * @throws ContextException
     */
      void init(MQContextEnvConfig contextEnvParams) throws ContextException;
    /**
     * 生产消息到MQ
     * @return
     * @throws ProducerException
     */
     ProduceResult produce(ProduceMessage  produceMessage)  throws ProducerException;

     ConsumeMessage consume()  throws ConsumerException;

      void  destroy();

}
