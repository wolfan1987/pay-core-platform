package org.andrew.commons.mqoper.dao;


import org.andrew.commons.mqoper.entitys.MqMessage;

/**
 * rocketMq消息读写接口。
 * Created by andrewliu on 2017/9/15.
 */
public interface MqMessageWriteDao extends MqMessageDao {
    MqMessage save(MqMessage message);

    MqMessage update(MqMessage message);

    void deleteById(Long id);

    MqMessage updateByUuid(MqMessage mqMessage);
}
