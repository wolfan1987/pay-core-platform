package org.andrew.commons.mqpoer.api;

import org.andrew.commons.exception.mq.ConsumerException;
import org.andrew.commons.mqpoer.entitys.ConsumeMessage;

/**
 * @Author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  为消息处理前与后添加处理点
 * @Date: Created in 2018/7/10 9:42
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface ConsumerExt<T> {

    /**
     * 处理消息前的预处理
     * @param consumeMessage
     * @return
     */
    boolean   preProcessor(T consumeMessage)  throws ConsumerException;

    /**
     * 消息处理完之后的业务操作
     * @param consumeMessage
     * @return
     * @throws ConsumerException
     */
    boolean   endProcessor(T consumeMessage) throws ConsumerException;
}
