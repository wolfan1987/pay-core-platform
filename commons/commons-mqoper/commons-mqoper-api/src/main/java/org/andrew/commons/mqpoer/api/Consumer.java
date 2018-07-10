package org.andrew.commons.mqpoer.api;

import org.andrew.commons.exception.mq.ConsumerException;

/**
 * @author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  消息消费基本接口
 * @Date: Created in 2018/7/7 10:49
 * @since  0.0.1
 * @version  0.0.1
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Consumer<T,C> {

    void  setConfig(C  config);
    /**
     * 启动消费服务
     * @return
     */
    void  doStart() throws ConsumerException;

    /**
     * 关闭消费服务
     * @return
     */
    void  doShutDown();

    /**
     * 处理消息
     * @param consumeMessage
     */
    void     processor(T consumeMessage) throws ConsumerException;

}
