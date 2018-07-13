package org.andrew.commons.mqoper.api;

import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.exception.mq.ProducerException;

import java.io.UnsupportedEncodingException;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description: 生产消息时，为生产前和后添加处理点
 * @Date: Created in 2018/7/10 9:50
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface ProducerExt<T> {

    /**
     * 处理消息前的预处理
     * @param produceMessage
     * @return
     */
    boolean   preProcessor(T produceMessage) throws ProducerException, UnsupportedEncodingException;

    /**
     * 消息处理完之后的业务操作
     * @param produceMessage
     * @return
     * @throws ConsumerException
     */
    boolean  endProcessor(T produceMessage) throws ProducerException;
}
