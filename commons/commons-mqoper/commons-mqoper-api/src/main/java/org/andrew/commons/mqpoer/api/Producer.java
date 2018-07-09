package org.andrew.commons.mqpoer.api;

import org.andrew.commons.mqpoer.entitys.ProduceMessage;
import org.andrew.commons.mqpoer.entitys.ProduceResult;

/**
 * @author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  消息生产基本接口
 * @Date: Created in 2018/7/7 10:49
 * @since  0.0.1
 * @version  0.0.1
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Producer<P,R> {

    boolean  start();

    R  send( P  produceMessage);

    boolean  stop();

}
