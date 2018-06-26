package org.andrew.commons.wechat.vo.card.update;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 卡信息更新实体。
 *
 * @author andrewliu
 */
public class CardUpdateInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "card_id")
    private String cardId;

    @JSONField(name = "member_card")
    private MemberCardVo memberCard;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public MemberCardVo getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(MemberCardVo memberCard) {
        this.memberCard = memberCard;
    }
}
