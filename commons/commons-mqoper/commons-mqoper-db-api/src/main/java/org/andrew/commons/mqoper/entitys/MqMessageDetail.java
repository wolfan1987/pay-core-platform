package org.andrew.commons.mqoper.entitys;



import org.andrew.commons.database.dao.Model;

import java.util.Date;

public class MqMessageDetail implements Model<Long> {
    /**
     * 主键，自动增长。
     */
    private Long id;

    /**
     * 消费组。
     */
    private String groupName;

    /**
     * 消息ID。
     */
    private String msgId;

    /**
     * 消费者IP地址。
     */
    private String ip;

    /**
     * 消费状态 1：成功 、 0：失败。
     */
    private String status;

    /**
     * 消费模式。
     */
    private String model;

    /**
     * 消费日期。
     */
    private Date createTime;

    /**
     * 名称。
     */
    private String instanceName;

    @Override
    public Long getKey() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
