package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 刷卡功能结构体。
 *
 * @author andrewliu
 */
public class SwipCard {

    /**
     * 是否设置该会员卡支持拉出微信支付刷卡界面。
     */
    @JSONField(name = "is_swipe_card")
    private Boolean isSwipeCard;

    /**
     * 是否设置该会员卡中部的按钮同时支持微信支付刷卡和会员卡二维码。
     */
    @JSONField(name = "is_pay_and_qrcode")
    private Boolean isPayAndQrcode;

    public Boolean isSwipeCard() {
        return isSwipeCard;
    }

    public void setSwipeCard(Boolean swipeCard) {
        isSwipeCard = swipeCard;
    }

    public Boolean isPayAndQrcode() {
        return isPayAndQrcode;
    }

    public void setPayAndQrcode(Boolean payAndQrcode) {
        isPayAndQrcode = payAndQrcode;
    }
}
