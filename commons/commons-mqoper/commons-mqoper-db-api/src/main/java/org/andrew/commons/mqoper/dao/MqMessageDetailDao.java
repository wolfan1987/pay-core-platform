package org.andrew.commons.mqoper.dao;



import org.andrew.commons.mqoper.entitys.MqMessageDetail;

import java.util.List;

/**
 * MQ消息消费详情信息-只读接口。
 *
 * @author andrewliu 2017年11月16日
 */
public interface MqMessageDetailDao {
    List<MqMessageDetail> findByMsgId(String msgId);
}
