package org.andrew.commons.mqoper.vos;


import org.andrew.commons.mqoper.entitys.MqMessage;

/**
 * 消息VO。
 */
public class MqMessageVo extends MqMessage {

    private String createTimeBegin;
    private String createTimeEnd;

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
