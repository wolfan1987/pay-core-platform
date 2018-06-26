package org.andrew.commons.wechat.model.card;

import java.io.Serializable;

/**
 * 商品信息。
 *
 * @author andrewliu
 */
public class Sku implements Serializable {

    /**
     * 卡券库存的数量，上限为100000000。
     */
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sku{" + "quantity=" + quantity + '}';
    }
}
