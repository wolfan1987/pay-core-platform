package org.andrew.commons.mqoper.dao;


import org.andrew.commons.mqoper.entitys.MqMessageDetail;

/**
 * MQ消息消费详情信息-读写。
 *
 * @author andrewliu 2017年11月16日
 */
public interface MqMessageDetailWriteDao extends MqMessageDetailDao {
    MqMessageDetail save(MqMessageDetail mqMessageDetail);
}
