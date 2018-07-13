package org.andrew.commons.mqoper.api;


import org.andrew.commons.exception.mq.ProducerException;
import org.andrew.commons.mqoper.config.Config;

/**
 * @author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  消息生产基本接口
 * @Date: Created in 2018/7/7 10:49
 * @since  0.0.1
 * @version  0.0.1
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Producer<T,R,C extends Config>  {

    void  setConfig(C  config);

    C  getConfig();

    void  doStart() throws ProducerException;

    R  doSend( T  produceMessage)throws ProducerException;

    void  doShutDown() throws ProducerException;

}
