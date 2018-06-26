package org.andrew.commons.wechat.vo.member.update;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 控制原生消息结构体，包含各字段的消息控制字段。
 *
 * @author andrewliu
 */
public class NotifyOptionalVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 积分变动时是否触发系统模板消息，默认为true。
     */
    @JSONField(name = "is_notify_bonus")
    private Boolean isNotifyBonus;

    /**
     * 余额变动时是否触发系统模板消息，默认为true。
     */
    @JSONField(name = "is_notify_balance")
    private Boolean isNotifyBalance;

    /**
     * 自定义group1变动时是否触发系统模板消息，默认为false。（2、3同理）。
     */
    @JSONField(name = "is_notify_custom_field1")
    private Boolean isNotifyCustomField1;

    public Boolean getNotifyBonus() {
        return isNotifyBonus;
    }

    public void setNotifyBonus(Boolean notifyBonus) {
        isNotifyBonus = notifyBonus;
    }

    public Boolean getNotifyBalance() {
        return isNotifyBalance;
    }

    public void setNotifyBalance(Boolean notifyBalance) {
        isNotifyBalance = notifyBalance;
    }

    public Boolean getNotifyCustomField1() {
        return isNotifyCustomField1;
    }

    public void setNotifyCustomField1(Boolean notifyCustomField1) {
        isNotifyCustomField1 = notifyCustomField1;
    }
}
