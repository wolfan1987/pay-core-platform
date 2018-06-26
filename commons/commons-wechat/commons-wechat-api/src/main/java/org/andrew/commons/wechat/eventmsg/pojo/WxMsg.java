package org.andrew.commons.wechat.eventmsg.pojo;

/**
 * 微信消息体。
 */
public class WxMsg {

    /**
     * 发送方微信号（开发者）。
     */
    private String toUser;

    /**
     * 接收方帐号。
     */
    private String fromUser;

    /**
     * 消息创建时间。
     */
    private String createDt;

    /**
     * 消息类型。
     */
    private String msgType;

    /**
     * 微信消息体构造函数。
     */
    public WxMsg(String toUser, String fromUser, String createDt, String msgType) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.createDt = createDt;
        this.msgType = msgType;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "WxMsg{" + "toUser='" + toUser + '\'' + ", fromUser='" + fromUser + '\'' +
               ", createDt='" + createDt + '\'' + ", msgType='" + msgType + '\'' + '}';
    }
}
