package org.andrew.commons.mqoper.dao.impl;



import org.andrew.commons.mqoper.dao.MqMessageDetailWriteDao;
import org.andrew.commons.mqoper.entitys.MqMessageDetail;
import org.andrew.commons.mqoper.mapper.MessageDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * MQ消息详情接口实现。
 *
 * @author lijiguang 2017年11月16日
 */

public class MqMessageDetailDaoImpl implements MqMessageDetailWriteDao {
    @Autowired
    private MessageDetailMapper messageDetailMapper;

    @Override
    public MqMessageDetail save(
        MqMessageDetail mqMessageDetail) {
        messageDetailMapper.save(mqMessageDetail);
        return mqMessageDetail;
    }

    @Override
    public List<MqMessageDetail> findByMsgId(String msgId) {
        return messageDetailMapper.findByMsgId(msgId);
    }
}
