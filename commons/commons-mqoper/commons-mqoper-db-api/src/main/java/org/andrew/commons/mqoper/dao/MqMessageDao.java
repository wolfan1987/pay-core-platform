package org.andrew.commons.mqoper.dao;


import org.andrew.commons.database.pagehelper.Page;
import org.andrew.commons.mqoper.entitys.MqMessage;
import org.andrew.commons.mqoper.vos.MqMessageVo;

import java.util.List;

/**
 * 系统配置。
 * Created by lijiguang on 2017/8/15.
 */
public interface MqMessageDao {

    public MqMessage findById(Long id);

    public MqMessage findMsg(MqMessage mqMessage);

    public List<MqMessage> find(MqMessageVo mqMessage);

    public Page<MqMessage> find(MqMessageVo mqMessage, Page<MqMessage> page);

}
