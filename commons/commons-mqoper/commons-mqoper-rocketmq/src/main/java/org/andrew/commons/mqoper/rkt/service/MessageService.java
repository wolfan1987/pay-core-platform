package org.andrew.commons.mqoper.rkt.service;


import org.andrew.commons.mqoper.dao.MqMessageDetailWriteDao;
import org.andrew.commons.mqoper.dao.MqMessageWriteDao;
import org.andrew.commons.mqoper.entitys.MqMessage;
import org.andrew.commons.mqoper.entitys.MqMessageDetail;
import org.springframework.stereotype.Service;

/**
 * MQ消息数据库持久化操作。
 *
 * @author lijiguang 2017年9月15日
 */
@Service
public class MessageService {
   // @Reference(version = "1.0.0")
    private MqMessageWriteDao messageWriteDao;
   // @Reference(version = "1.0.0")
    private MqMessageDetailWriteDao mqMessageDetailWriteDao;

    public MqMessage findById(Long id) {
        return messageWriteDao.findById(id);
    }

    public MqMessage findMsg(MqMessage mqMessage) {
        return messageWriteDao.findMsg(mqMessage);
    }

    public MqMessage save(MqMessage message) {
        return messageWriteDao.save(message);
    }

    public MqMessageDetail save(MqMessageDetail mqMessageDetail) {
        return mqMessageDetailWriteDao.save(mqMessageDetail);
    }

    public MqMessage update(MqMessage message) {
        return messageWriteDao.update(message);
    }

    public void deleteById(Long id) {
        messageWriteDao.deleteById(id);
    }

    public MqMessage updateByUuid(MqMessage mqMessage) {
        return messageWriteDao.updateByUuid(mqMessage);
    }
}
