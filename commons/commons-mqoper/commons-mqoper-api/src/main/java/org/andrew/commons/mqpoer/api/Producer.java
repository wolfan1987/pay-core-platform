package org.andrew.commons.mqpoer.api;


import org.andrew.commons.exception.mq.ProducerException;

/**
 * @author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  消息生产基本接口
 * @Date: Created in 2018/7/7 10:49
 * @since  0.0.1
 * @version  0.0.1
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Producer<T,R,C> {

    void  setConfig(C  config);

    void  doStart() throws ProducerException;

    R  doSend( T  produceMessage)throws ProducerException;

    void  doShutDown() throws ProducerException;

}
