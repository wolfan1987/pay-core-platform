package org.andrew.commons.mqoper.rkt.model;

/**
 * 文本消息实体。
 * @author andrewliu
 */
public class TextMsgVo extends MqMessageVo {

    /**
     * 消息内容。
     */
    private String message;

    /**
     * 消息keys，业务相关的唯一标识，如订单号。
     */
    private String keys;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}
