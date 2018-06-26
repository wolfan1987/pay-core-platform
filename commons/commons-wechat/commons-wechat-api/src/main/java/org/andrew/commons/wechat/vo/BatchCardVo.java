package org.andrew.commons.wechat.vo;



import org.andrew.commons.wechat.model.card.Card;

import java.io.Serializable;

/**
 * 批量查询卡信息Vo。
 *
 * @author andrewliu
 */
public class BatchCardVo implements Serializable {

    /**
     * 微信卡券ID。
     */
    private String cardId;

    /**
     * 微信卡券信息。
     */
    private Card card;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "BatchCardVo{" + "cardId='" + cardId + '\'' + ", card=" + card + '}';
    }
}
