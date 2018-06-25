package org.andrew.commons.mqoper.mapper;


import org.andrew.commons.mqoper.entitys.MqMessageDetail;

import java.util.List;

/**
 * MQ消息详情数据处理接口。
 *
 * @author andrewliu 2017年11月16日
 */
public interface MessageDetailMapper {
    /**
     * 查询。
     *
     * @param msgId 消息ID
     * @return 集合
     */
    public List<MqMessageDetail> findByMsgId(String msgId);

    /**
     * 新增。
     */
    public void save(MqMessageDetail mqMessageDetail);
}
