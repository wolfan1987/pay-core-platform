package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 积分规则。
 *
 * @author andrewliu
 */
public class BonusRule implements Serializable {

    /**
     * 构造函数。
     *
     * @param builder Builder
     */
    public BonusRule(Builder builder) {
        this.costMoneyUnit = builder.costMoneyUnit;
        this.increaseBonus = builder.increaseBonus;
        this.maxIncreaseBonus = builder.maxIncreaseBonus;
        this.initIncreaseBonus = builder.initIncreaseBonus;
        this.costBonusUnit = builder.costBonusUnit;
        this.reduceMoney = builder.reduceMoney;
        this.leastMoneyToUseBonus = builder.leastMoneyToUseBonus;
        this.maxReduceBonus = builder.maxReduceBonus;
    }

    /**
     * 消费金额。以分为单位。
     */
    @JSONField(name = "cost_money_unit")
    private Integer costMoneyUnit;

    /**
     * 对应增加的积分。
     */
    @JSONField(name = "increase_bonus")
    private Integer increaseBonus;

    /**
     * 用户单次可获取的积分上限。
     */
    @JSONField(name = "max_increase_bonus")
    private Integer maxIncreaseBonus;

    /**
     * 初始设置积分。
     */
    @JSONField(name = "init_increase_bonus")
    private Integer initIncreaseBonus;

    /**
     * 每使用5积分。
     */
    @JSONField(name = "cost_bonus_unit")
    private Integer costBonusUnit;

    /**
     * 抵扣xx元，（这里以分为单位。
     */
    @JSONField(name = "reduce_money")
    private Integer reduceMoney;

    /**
     * 抵扣条件，满xx元（这里以分为单位）可用。
     */
    @JSONField(name = "least_money_to_use_bonus")
    private Integer leastMoneyToUseBonus;

    /**
     * 抵扣条件，单笔最多使用xx积分。
     */
    @JSONField(name = "max_reduce_bonus")
    private Integer maxReduceBonus;

    public Integer getCostMoneyUnit() {
        return costMoneyUnit;
    }

    public void setCostMoneyUnit(Integer costMoneyUnit) {
        this.costMoneyUnit = costMoneyUnit;
    }

    public Integer getIncreaseBonus() {
        return increaseBonus;
    }

    public void setIncreaseBonus(Integer increaseBonus) {
        this.increaseBonus = increaseBonus;
    }

    public Integer getMaxIncreaseBonus() {
        return maxIncreaseBonus;
    }

    public void setMaxIncreaseBonus(Integer maxIncreaseBonus) {
        this.maxIncreaseBonus = maxIncreaseBonus;
    }

    public Integer getInitIncreaseBonus() {
        return initIncreaseBonus;
    }

    public void setInitIncreaseBonus(Integer initIncreaseBonus) {
        this.initIncreaseBonus = initIncreaseBonus;
    }

    public Integer getCostBonusUnit() {
        return costBonusUnit;
    }

    public void setCostBonusUnit(Integer costBonusUnit) {
        this.costBonusUnit = costBonusUnit;
    }

    public Integer getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(Integer reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public Integer getLeastMoneyToUseBonus() {
        return leastMoneyToUseBonus;
    }

    public void setLeastMoneyToUseBonus(Integer leastMoneyToUseBonus) {
        this.leastMoneyToUseBonus = leastMoneyToUseBonus;
    }

    public Integer getMaxReduceBonus() {
        return maxReduceBonus;
    }

    public void setMaxReduceBonus(Integer maxReduceBonus) {
        this.maxReduceBonus = maxReduceBonus;
    }

    @Override
    public String toString() {
        return "BonusRule{" + "costMoneyUnit=" + costMoneyUnit + ", increaseBonus=" +
               increaseBonus + ", maxIncreaseBonus=" + maxIncreaseBonus + ", initIncreaseBonus=" +
               initIncreaseBonus + ", costBonusUnit=" + costBonusUnit + ", reduce_money=" +
               reduceMoney + ", leastMoneyToUseBonus=" + leastMoneyToUseBonus +
               ", maxReduceBonus=" + maxReduceBonus + '}';
    }

    public static class Builder {
        private Integer costMoneyUnit;
        private Integer increaseBonus;
        private Integer maxIncreaseBonus;
        private Integer initIncreaseBonus;
        private Integer costBonusUnit;
        private Integer reduceMoney;
        private Integer leastMoneyToUseBonus;
        private Integer maxReduceBonus;

        public Builder costMoneyUnit(int costMoneyUnit) {
            this.costMoneyUnit = costMoneyUnit;
            return this;
        }

        public Builder increaseBonus(int increaseBonus) {
            this.increaseBonus = increaseBonus;
            return this;
        }

        public Builder maxIncreaseBonus(int initIncreaseBonus) {
            this.maxIncreaseBonus = maxIncreaseBonus;
            return this;
        }

        public Builder initIncreaseBonus(int initIncreaseBonus) {
            this.initIncreaseBonus = initIncreaseBonus;
            return this;
        }

        public Builder reduceMoney(int reduceMoney) {
            this.reduceMoney = reduceMoney;
            return this;
        }

        public Builder leastMoneyToUseBonus(int leastMoneyToUseBonus) {
            this.leastMoneyToUseBonus = leastMoneyToUseBonus;
            return this;
        }

        public Builder maxReduceBonus(int maxReduceBonus) {
            this.maxReduceBonus = maxReduceBonus;
            return this;
        }

        public Builder costBonusUnit(int costBonusUnit) {
            this.costBonusUnit = costBonusUnit;
            return this;
        }

        public BonusRule build() {
            return new BonusRule(this);
        }

    }
}
