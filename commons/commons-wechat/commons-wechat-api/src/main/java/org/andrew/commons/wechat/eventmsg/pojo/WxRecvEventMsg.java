package org.andrew.commons.wechat.eventmsg.pojo;

/**
 * 接受到的事件消息。
 */
public class WxRecvEventMsg extends WxRecvMsg {

    /**
     * 事件类型。
     */
    private String event;

    /**
     * 维码的ticket。
     * 用来跟微信换取二维码图片
     */
    private String ticket;
    /**
     * 经度。
     */
    private String latitude;
    /**
     * 纬度。
     */
    private String longitude;
    /**
     * 精确度。
     */
    private String precision;

    /**
     * 发送的状态。
     **/
    private String status;

    /**
     * 用户自定义会员卡编号。
     **/
    private String userCardCode;

    /**
     * 转赠事件中，接收卡券用户的openid。
     */
    private String friendUserName;

    /**
     * 转赠事件中，是否转赠退回，0代表不是，1代表是。
     */
    private String isReturnBack;

    /**
     * 转赠事件中，是否是群转赠。
     */
    private String isChatRoom;

    /**
     * 微信会员卡cardId。
     **/
    private String cardId;

    /**
     * 审核不通过的原因。
     */
    private String refuseReason;


    /**
     * 构造函数。
     *
     * @param builder Builder
     */
    public WxRecvEventMsg(Builder builder) {
        super(builder.toUser, builder.fromUser, builder.createDt, builder.msgType, builder.msgId);
        this.event = builder.event;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.precision = builder.precision;
        this.ticket = builder.ticket;
        this.status = builder.status;
        this.userCardCode = builder.userCardCode;
        this.cardId = builder.cardId;
        this.refuseReason = builder.refuseReason;
        this.friendUserName = builder.friendUserName;
        this.isReturnBack = builder.isReturnBack;
        this.isChatRoom = builder.isChatRoom;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserCardCode() {
        return userCardCode;
    }

    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    public String getIsReturnBack() {
        return isReturnBack;
    }

    public void setIsReturnBack(String isReturnBack) {
        this.isReturnBack = isReturnBack;
    }

    public String getIsChatRoom() {
        return isChatRoom;
    }

    public void setIsChatRoom(String isChatRoom) {
        this.isChatRoom = isChatRoom;
    }

    public static class Builder {

        private String event;
        private String ticket;
        private String latitude;
        private String longitude;
        private String precision;
        private String status;
        private String userCardCode;
        private String cardId;
        private String refuseReason;
        private String friendUserName;
        private String isReturnBack;
        private String isChatRoom;

        /**
         * 消息id，64位整型。
         */
        private String msgId;

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

        public Builder latitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder event(String event) {
            this.event = event;
            return this;
        }

        public Builder ticket(String ticket) {
            this.ticket = ticket;
            return this;
        }

        public Builder longitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder precision(String precision) {
            this.precision = precision;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder userCardCode(String userCardCode) {
            this.userCardCode = userCardCode;
            return this;
        }

        public Builder cardId(String cardId) {
            this.cardId = cardId;
            return this;
        }

        public Builder refuseReason(String refuseReason) {
            this.refuseReason = refuseReason;
            return this;
        }

        public Builder msgId(String msgId) {
            this.msgId = msgId;
            return this;
        }

        public Builder toUser(String toUser) {
            this.toUser = toUser;
            return this;
        }

        public Builder fromUser(String fromUser) {
            this.fromUser = fromUser;
            return this;
        }

        public Builder createDt(String createDt) {
            this.createDt = createDt;
            return this;
        }

        public Builder msgType(String msgType) {
            this.msgType = msgType;
            return this;
        }

        public Builder friendUserName(String friendUserName) {
            this.friendUserName = friendUserName;
            return this;
        }

        public Builder isReturnBack(String isReturnBack) {
            this.isReturnBack = isReturnBack;
            return this;
        }

        public Builder isChatRoom(String isChatRoom) {
            this.isChatRoom = isChatRoom;
            return this;
        }

        public WxRecvEventMsg build() {
            return new WxRecvEventMsg(this);
        }
    }
}
