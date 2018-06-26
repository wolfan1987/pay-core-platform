package org.andrew.commons.wechat.service;


import org.andrew.commons.wechat.vo.EventHandlerVo;

/**
 * 微信业务处理接口。
 *
 * @author andrewliu
 */
public interface EventHandlerService {

    /**
     * 处理微信回调事件。
     */
    void execute(EventHandlerVo eventHandlerVo);
}
