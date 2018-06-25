package org.andrew.commons.mqoper.rkt.executes.producer;


import org.andrew.commons.mqoper.entitys.MqMessage;
import org.andrew.commons.mqoper.rkt.model.TextMsgVo;
import org.andrew.commons.mqoper.rkt.scheduler.Job;
import org.andrew.commons.mqoper.rkt.scheduler.caller.RmqTextCaller;
import org.andrew.commons.mqoper.rkt.service.MessageService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

/**
 * 文本消息提供者。
 *
 * @author andrewliu
 */
public class TextMessageProducer extends AbstractRocketMqProducer<TextMsgVo> {

    private static final Logger logger = LoggerFactory.getLogger(TextMessageProducer.class);

    @Autowired
    private MessageService messageService;

    public TextMessageProducer(int intervalMillis) {
        super(intervalMillis);
    }

    public TextMessageProducer(String jobQueueName, int intervalMillis) {
        super(jobQueueName, intervalMillis);
    }

    /**
     * 发送文本消息。
     *
     * @param keys keys
     * @param msg  发送消息
     * @return 处理结果是否成功
     */
    @Override
    public boolean doSend(String keys, String msg) {
        TextMsgVo textMsg = new TextMsgVo();
        textMsg.setTopic(this.getTopic());
        textMsg.setTags(this.getTags());
        textMsg.setMessage(msg);
        textMsg.setKeys(keys);
        MqMessage mqMessage = null;
        if (isPersist()) {
            mqMessage = this.saveMessage(textMsg);
        }
        //此消息无需重复发送至MQ服务器
        if (mqMessage == null) {
            return false;
        }
        SendResult sendResult = this.sendTextMsg(msg, mqMessage.getUuid());
        logger.info("生产消息发送到MQ服务器keys:{}", keys);
        logger.info("生产消息发送到MQ服务器sendResult:{}", sendResult);
        // 当消息发送失败时，加入定时队列进行重试
        boolean isSuccess = true;
        if (sendResult == null || sendResult.getSendStatus() != SendStatus.SEND_OK) {
            isSuccess = false;
            if (this.isUseJobSchedule()) {
                logger.error("发送消息失败,msg:{}", textMsg);
               Job job = this.getJobScheduler().getJob(mqMessage.getUuid());
                if (job == null) {
                    RmqTextCaller jobCaller = new RmqTextCaller(this, textMsg);
                    this.getJobScheduler().addJob(
                        mqMessage.getUuid(), intervalMillis, jobCaller, getMaxRunRounds());
                }
            }
        }
        if (isSuccess) {
            //消息发送成功
            mqMessage.setStatus(ProducerConstant.SEND_SUCCESS);
            mqMessage.setMsgId(sendResult.getMsgId());
        } else {
            mqMessage.setStatus(ProducerConstant.SEND_FAIL);
        }
        mqMessage.setUpdateTime(new Date());
        if (isPersist()) {
            messageService.update(mqMessage);
        }
        return isSuccess;
    }

    /**
     * 发送消息到rocketMQ。
     *
     * @param msg  文本消息。
     * @param keys 业务唯一标识，如订单号。
     * @return 发送状态。
     */

    private SendResult sendTextMsg(String msg, String keys) {
        SendResult sendResult = null;
        try {
            Message mqMessage = new Message(
                this.getTopic(), this.getTags(), msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            mqMessage.setKeys(keys);
            sendResult = this.send(mqMessage);
        } catch (Exception ex) {
            logger.error("生产消息发送到MQ服务器错误，消息内容[{}]", msg, ex);
        }
        return sendResult;
    }

    /**
     * 保存到数据库。
     *
     * @param textMsgVo 消息对象。
     * @return 消息对应的数据库数据。
     */
    private MqMessage saveMessage(TextMsgVo textMsgVo) {
        MqMessage rocketMqMessage = new MqMessage();
        rocketMqMessage.setTopic(this.getTopic());
        rocketMqMessage.setKeys(textMsgVo.getKeys());
        rocketMqMessage.setMessage(textMsgVo.getMessage());
        rocketMqMessage.setTags(this.getTags());
        rocketMqMessage.setStatus(ProducerConstant.SEND_ING);
        rocketMqMessage.setCreateTime(new Date());
        rocketMqMessage.setConsumerNum(0);
        rocketMqMessage.setUuid(UUID.randomUUID().toString());
        MqMessage mqMessage = messageService.findMsg(rocketMqMessage);
        //是否是重复消息
        if (mqMessage != null) {
            //发送失败的信息则重新发送
            if (mqMessage.getStatus().equals(ProducerConstant.SEND_ING)) {
                return mqMessage;
            } else {
                return null;//消息重复无需发送至MQ服务器
            }
        }
        return messageService.save(rocketMqMessage);
    }
}
