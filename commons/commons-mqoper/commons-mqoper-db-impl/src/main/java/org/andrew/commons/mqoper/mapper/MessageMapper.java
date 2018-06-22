package org.andrew.commons.mqoper.mapper;



import org.andrew.commons.mqoper.entitys.MqMessage;
import org.andrew.commons.mqoper.vos.MqMessageVo;

import java.util.List;

/**
 * mybatis 对应的对mq消息的增删改查。
 *
 * @author lijiguang 2017年9月15日
 */
public interface MessageMapper {

    void save(MqMessage message);

    void update(MqMessage message);

    MqMessage findById(Long id);

    void deleteById(Long id);

    MqMessage findMsg(MqMessage mqMessage);

    void updateByUuid(MqMessage mqMessage);

    List<MqMessage> find(MqMessageVo mqMessageVo);
}
