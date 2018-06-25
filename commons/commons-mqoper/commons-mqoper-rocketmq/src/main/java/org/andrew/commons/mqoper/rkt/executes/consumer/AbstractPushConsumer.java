package org.andrew.commons.mqoper.rkt.executes.consumer;


import org.andrew.commons.mqoper.entitys.MqMessage;
import org.andrew.commons.mqoper.entitys.MqMessageDetail;
import org.andrew.commons.mqoper.rkt.api.Consumer;
import org.andrew.commons.mqoper.rkt.executes.producer.ProducerConstant;
import org.andrew.commons.mqoper.rkt.service.MessageService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 消费者订阅模板，提供消费接口外部调用。
 *
 * @author andrewliu
 */
public abstract class AbstractPushConsumer extends DefaultMQPushConsumer implements Consumer {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPushConsumer.class);

    @Autowired
    private MessageService messageService;
    /**
     * 订阅的子主题，用来过滤主题消息，对应生产者的tags。
     */
    private String         suvExpression;

    private String topic;

    private boolean broadcasting = false;

    public String getSuvExpression() {
        return suvExpression;
    }

    public void setSuvExpression(String suvExpression) {
        this.suvExpression = suvExpression;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isBroadcasting() {
        return broadcasting;
    }

    public void setBroadcasting(boolean broadcasting) {
        this.broadcasting = broadcasting;
    }

    @Override
    public void doStart() throws MQClientException {
        Assert.notNull(getNamesrvAddr(), "name server is not null");
        Assert.notNull(getConsumerGroup(), "consumer group is not null");
        Assert.notNull(topic, "topic is not null");
        Assert.notNull(suvExpression, "suvExpression is not null");
        this.setVipChannelEnabled(false);
        /*
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费。
         * 如果非第一次启动，那么按照上次消费的位置继续消费。
         * 这里设置的是一个consumer的消费策略
         * CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
         * CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
         * CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
         */
        this.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);//跳过已经消费的历史消息
        if (broadcasting) {
            this.setMessageModel(MessageModel.BROADCASTING);//广播模式
        } else {
            this.setMessageModel(MessageModel.CLUSTERING);//集群模式
        }
        this.subscribe(topic, suvExpression);
        //使用线程池控制业务处理线程，采用多线程处理消费者业务，增大消费消息吞吐量。
        this.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(
                List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                logger.info("接收到新的消息size: {}", msgs.size());
                logger.info("接收到新的消息: {}", msgs);
                boolean reFlag = false;//返回结果
                try {
                    for (MessageExt msg : msgs) {
                        ConsumerMessage consumerMessage = new ConsumerMessage();
                        consumerMessage.setBody(new String(msg.getBody(), "UTF-8"));
                        consumerMessage.setTopic(msg.getTopic());
                        consumerMessage.setTags(msg.getTags());
                        consumerMessage.setMsgId(msg.getMsgId());
                        consumerMessage.setQueueId(msg.getQueueId());
                        MqMessage mqMessage = new MqMessage();
                        mqMessage.setUuid(msg.getKeys());
                        mqMessage.setMsgId(consumerMessage.getMsgId());
                        mqMessage.setUpdateTime(new Date());
                        //消费结果详情
                        MqMessageDetail mqMessageDetail = new MqMessageDetail();
                        //消费日期
                        mqMessageDetail.setCreateTime(new Date());
                        //组名称
                        mqMessageDetail.setGroupName(getConsumerGroup());
                        //消费模式
                        mqMessageDetail.setModel(getMessageModel().getModeCN());
                        //客户端IP
                        mqMessageDetail.setIp(getClientIP());
                        //instanceName
                        mqMessageDetail.setInstanceName(getInstanceName());
                        //消息ID
                        mqMessageDetail.setMsgId(msg.getMsgId());
                        //此批次消息有一条消费成功即代表消费消息成功
                        if (execute(consumerMessage)) {
                            //消费成功
                            mqMessageDetail.setStatus(ProducerConstant.CONSUMER_SUCCESS);
                            reFlag = true;
                        } else {
                            //消费失败
                            mqMessageDetail.setStatus(ProducerConstant.CONSUMER_FAIL);
                        }
                        //消息已经被消费
                        mqMessage.setStatus(ProducerConstant.CONSUMERED);
                        //更新消息消费状态
                        messageService.updateByUuid(mqMessage);
                        messageService.save(mqMessageDetail);
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
                if (reFlag) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        this.start();
        logger.info("push Consumer Started.");
    }

    @Override
    public void doShutdown() {
        this.shutdown();
    }

}
