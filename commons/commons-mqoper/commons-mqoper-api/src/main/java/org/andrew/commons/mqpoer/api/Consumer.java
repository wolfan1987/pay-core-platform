package org.andrew.commons.mqpoer.api;

/**
 * @author AndrewLiu (liudaan@chinaexpresscard.com)
 * @Description:  消息消费基本接口
 * @Date: Created in 2018/7/7 10:49
 * @since  0.0.1
 * @version  0.0.1
 * @Modifyed By:
 * @Other: A Lucky Man
 */
public interface Consumer<M> {




    boolean  start();

    boolean  stop();

    void  consumeMsg(M m);

}
