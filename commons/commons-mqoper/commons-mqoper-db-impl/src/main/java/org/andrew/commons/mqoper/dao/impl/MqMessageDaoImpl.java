package org.andrew.commons.mqoper.dao.impl;


import org.andrew.commons.database.pagehelper.Page;
import org.andrew.commons.database.pagehelper.PageHelper;
import org.andrew.commons.mqoper.dao.MqMessageWriteDao;
import org.andrew.commons.mqoper.entitys.MqMessage;
import org.andrew.commons.mqoper.mapper.MessageMapper;
import org.andrew.commons.mqoper.vos.MqMessageVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * rocketMq消息接口实现类。
 *
 * @author andrewliu
 */

public class MqMessageDaoImpl implements MqMessageWriteDao {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public MqMessage save(MqMessage message) {
        messageMapper.save(message);
        return message;
    }

    @Override
    public MqMessage update(MqMessage message) {
        messageMapper.update(message);
        return message;
    }

    @Override
    public void deleteById(Long id) {
        messageMapper.deleteById(id);
    }

    @Override
    public MqMessage updateByUuid(MqMessage mqMessage) {
        messageMapper.updateByUuid(mqMessage);
        return mqMessage;
    }

    @Override
    public MqMessage findById(Long id) {
        return messageMapper.findById(id);
    }

    @Override
    public MqMessage findMsg(MqMessage mqMessage) {
        return messageMapper.findMsg(mqMessage);
    }

    @Override
    public List<MqMessage> find(MqMessageVo mqMessageVo) {
        return messageMapper.find(mqMessageVo);
    }

    @Override
    public Page<MqMessage> find(
        MqMessageVo mqMessageVo, Page<MqMessage> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        messageMapper.find(mqMessageVo);
        return PageHelper.endPage();
    }
}
