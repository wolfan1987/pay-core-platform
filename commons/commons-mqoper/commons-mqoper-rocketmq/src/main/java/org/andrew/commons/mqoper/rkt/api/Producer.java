package org.andrew.commons.mqoper.rkt.api;

/**
 * 提供者接口。
 * @author andrewliu
 */
public interface Producer<T> {

    /**
     * 处理启动。
     */
    void doStart();

    /**
     * 关闭。
     */
    void doShutdown();

    /**
     * 发送消息。
     * @param msg 消息实体
     * @return 发送结果 true成功 false失败
     */
    boolean doSend(String keys, String msg);
}
