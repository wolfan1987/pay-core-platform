package org.andrew.commons.mqoper.rkt.executes.producer;

/**
 * MQ消息常量。
 */
public interface ProducerConstant {
    /**
     * 消息-状态-发送中。
     */
    String SEND_ING     = "0";
    /**
     * 消息-状态-发送成功。
     */
    String SEND_SUCCESS = "1";
    /**
     * 消息-状态-发送失败。
     */
    String SEND_FAIL    = "2";

    /**
     * 消息-状态-已经被消费。
     */
    String CONSUMERED = "3";

    /**
     * 消息详情-状态-消费成功。
     */
    String CONSUMER_SUCCESS = "1";
    /**
     * 消息详情-状态-消费失败。
     */
    String CONSUMER_FAIL    = "0";
}
