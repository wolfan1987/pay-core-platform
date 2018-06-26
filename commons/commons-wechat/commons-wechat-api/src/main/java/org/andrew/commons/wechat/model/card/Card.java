package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 会员卡实体。
 *
 * @author andrewliu
 */
public class Card implements Serializable {

    /**
     * 会员卡类型。
     */
    @JSONField(name = "card_type")
    private String cardType;

    /**
     * 会员卡信息。
     */
    @JSONField(name = "member_card")
    private MemberCard memberCard;

    public MemberCard getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" + "cardType='" + cardType + '\'' + ", memberCard=" + memberCard + '}';
    }
}
