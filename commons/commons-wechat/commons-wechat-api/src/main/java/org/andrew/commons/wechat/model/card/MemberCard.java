package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.annotation.JSONField;
import org.andrew.commons.wechat.constants.NameTypeEnum;


import java.io.Serializable;

/**
 * 微信会员卡必要信息。
 *
 * @author andrewliu
 */
public class MemberCard implements Serializable {

    public MemberCard() {
    }

    /**
     * 会员卡信息构造体。
     */
    public MemberCard(Builder builder) {
        this.backgroundPicUrl = builder.backgroundPicUrl;
        this.baseInfo = builder.baseInfo;
        this.prerogative = builder.prerogative;
        this.autoActivate = builder.autoActivate;
        this.wxActivate = builder.wxActivate;
        this.supplyBalance = builder.supplyBalance;
        this.bonusUrl = builder.bonusUrl;
        this.supplyBalance = builder.supplyBalance;
        this.balanceUrl = builder.balanceUrl;
        this.customField1 = builder.customField1;
        this.customField2 = builder.customField2;
        this.customField3 = builder.customField3;
        this.bonusCleared = builder.bonusCleared;
        this.balanceRules = builder.balanceRules;
        this.activateUrl = builder.activateUrl;
        this.customCell = builder.customCell;
        this.bonusRule = builder.bonusRule;
        this.discount = builder.discount;
        this.supplyBonus = builder.supplyBonus;
        this.bonusRules = builder.bonusRules;
    }

    /**
     * 基本的卡券数据，见下表，所有卡券类型通用。
     * json数据结构
     */
    @JSONField(name = "base_info")
    private BaseInfo baseInfo;

    /**
     * 会员卡特权说明,
     * 如：持白金会员卡到店消费，可享8折优惠。
     */
    private String prerogative;

    /**
     * 卡面设计请遵循微信会员卡自定义背景设计规范,像素大小控制在
     * 1000像素*600像素以下。
     */
    @JSONField(name = "background_pic_url")
    private String backgroundPicUrl;

    /**
     * 设置为true时用户领取会员卡后系统自动将其激活，无需调用激活接口。
     * 注：设置会员卡自动激活功能，需在创建会员卡时填入指定字段，
     * base_info中增加"auto_activate": true，获取cardid
     */
    @JSONField(name = "auto_activate")
    private boolean autoActivate = true;

    /**
     * 设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。
     * 填入该字段后仍需调用接口设置开卡项方可生效
     */
    @JSONField(name = "wx_activate")
    private boolean wxActivate;

    /**
     * 显示积分，填写true或false，如填写true，积分相关字段均为必填。
     */
    @JSONField(name = "supply_bonus")
    private boolean supplyBonus = false;

    /**
     * xxxx.com
     * 设置跳转外链查看积分详情。
     * 仅适用于积分无法通过激活接口同步的情况下使用该字段
     */
    @JSONField(name = "bonus_url")
    private String bonusUrl;

    /**
     * 是否支持储值，填写true或false。
     * 如填写true，储值相关字段均为必填
     */
    @JSONField(name = "supply_balance")
    private boolean supplyBalance;

    /**
     * 设置跳转外链查看余额详情。仅适用于余额无法通过激活接口同步的情况下使用该字段。
     * xxxx.com
     */
    @JSONField(name = "balance_url")
    private String balanceUrl;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     * json结构
     */
    @JSONField(name = "custom_field1")
    private CustomField customField1;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     * json结构
     */
    @JSONField(name = "custom_field2")
    private CustomField customField2;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     * json结构
     */
    @JSONField(name = "custom_field3")
    private CustomField customField3;

    /**
     * 积分清零规则
     * 每年年底12月30号积分清0。
     */
    @JSONField(name = "bonus_cleared")
    private String bonusCleared;

    /**
     * 积分规则。
     * 如：每消费一元获取1点积分
     */
    @JSONField(name = "bonus_rules")
    private String bonusRules;

    /**
     * 储值说明。
     * xxx.com
     */
    @JSONField(name = "balance_rules")
    private String balanceRules;

    /**
     * 激活会员卡的url。
     * xxx.com
     */
    @JSONField(name = "activate_url")
    private String activateUrl;

    /**
     * 自定义会员信息类目，会员卡激活后显示。
     */
    @JSONField(name = "custom_cell1")
    private CustomCell customCell;

    /**
     * 积分规则。用于微信买单功能。
     */
    @JSONField(name = "bonus_rule")
    private BonusRule bonusRule;

    /**
     * 折扣，该会员卡享受的折扣优惠,填10就是九折。
     */
    private Integer discount;

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public String getPrerogative() {
        return prerogative;
    }

    public void setPrerogative(String prerogative) {
        this.prerogative = prerogative;
    }

    public boolean isAutoActivate() {
        return autoActivate;
    }

    public void setAutoActivate(boolean autoActivate) {
        this.autoActivate = autoActivate;
    }

    public boolean isWxActivate() {
        return wxActivate;
    }

    public void setWxActivate(boolean wxActivate) {
        this.wxActivate = wxActivate;
    }

    public boolean isSupplyBonus() {
        return supplyBonus;
    }

    public void setSupplyBonus(boolean supplyBonus) {
        this.supplyBonus = supplyBonus;
    }

    public String getBonusUrl() {
        return bonusUrl;
    }

    public void setBonusUrl(String bonusUrl) {
        this.bonusUrl = bonusUrl;
    }

    public boolean isSupplyBalance() {
        return supplyBalance;
    }

    public void setSupplyBalance(boolean supplyBalance) {
        this.supplyBalance = supplyBalance;
    }

    public String getBalanceUrl() {
        return balanceUrl;
    }

    public void setBalanceUrl(String balanceUrl) {
        this.balanceUrl = balanceUrl;
    }

    public CustomField getCustomField1() {
        return customField1;
    }

    public void setCustomField1(CustomField customField1) {
        this.customField1 = customField1;
    }

    public CustomField getCustomField2() {
        return customField2;
    }

    public void setCustomField2(CustomField customField2) {
        this.customField2 = customField2;
    }

    public CustomField getCustomField3() {
        return customField3;
    }

    public void setCustomField3(CustomField customField3) {
        this.customField3 = customField3;
    }

    public String getBonusCleared() {
        return bonusCleared;
    }

    public void setBonusCleared(String bonusCleared) {
        this.bonusCleared = bonusCleared;
    }

    public String getBonusRules() {
        return bonusRules;
    }

    public void setBonusRules(String bonusRules) {
        this.bonusRules = bonusRules;
    }

    public String getBalanceRules() {
        return balanceRules;
    }

    public void setBalanceRules(String balanceRules) {
        this.balanceRules = balanceRules;
    }

    public String getActivateUrl() {
        return activateUrl;
    }

    public void setActivateUrl(String activateUrl) {
        this.activateUrl = activateUrl;
    }

    public CustomCell getCustomCell() {
        return customCell;
    }

    public void setCustomCell(CustomCell customCell) {
        this.customCell = customCell;
    }

    public BonusRule getBonusRule() {
        return bonusRule;
    }

    public void setBonusRule(BonusRule bonusRule) {
        this.bonusRule = bonusRule;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getBackgroundPicUrl() {
        return backgroundPicUrl;
    }

    public void setBackgroundPicUrl(String backgroundPicUrl) {
        this.backgroundPicUrl = backgroundPicUrl;
    }

    public static class Builder {

        private BaseInfo baseInfo;
        private String   backgroundPicUrl;
        private String   prerogative;
        private boolean autoActivate = true;
        private boolean wxActivate;
        private boolean supplyBonus = false;
        private String      bonusUrl;
        private boolean     supplyBalance;
        private String      balanceUrl;
        private CustomField customField1;
        private CustomField customField2;
        private CustomField customField3;
        private String      bonusCleared;
        private String      bonusRules;
        private String      balanceRules;
        private String      activateUrl;
        private CustomCell  customCell;
        private BonusRule   bonusRule;
        private Integer     discount;

        public Builder() {
        }

        public Builder balanceUrl(String balanceUrl) {
            this.balanceUrl = balanceUrl;
            return this;
        }

        public Builder baseInfo(BaseInfo baseInfo) {
            this.baseInfo = baseInfo;
            return this;
        }

        public Builder prerogative(String prerogative) {
            this.prerogative = prerogative;
            return this;
        }

        public Builder autoActivate(boolean autoActivate) {
            this.autoActivate = autoActivate;
            return this;
        }

        public Builder wxActivate(boolean wxActivate) {
            this.wxActivate = wxActivate;
            return this;
        }

        public Builder supplyBonus(boolean supplyBonus) {
            this.supplyBonus = supplyBonus;
            return this;
        }

        public Builder bonusUrl(String bonusUrl) {
            this.bonusUrl = bonusUrl;
            return this;
        }

        public Builder supplyBalance(boolean supplyBalance) {
            this.supplyBalance = supplyBalance;
            return this;
        }

        public Builder customField1(NameTypeEnum nameType, String url) {
            this.customField1 = new CustomField(nameType, url);
            return this;
        }

        public Builder customField2(NameTypeEnum nameType, String url) {
            this.customField2 = new CustomField(nameType, url);
            return this;
        }

        public Builder customField3(NameTypeEnum nameType, String url) {
            this.customField3 = new CustomField(nameType, url);
            return this;
        }

        public Builder bonusCleared(String bonusCleared) {
            this.bonusCleared = bonusCleared;
            return this;
        }

        public Builder bonusRules(String bonusRules) {
            this.bonusRules = bonusRules;
            return this;
        }

        public Builder balanceRules(String balanceRules) {
            this.balanceRules = balanceRules;
            return this;
        }

        public Builder activateUrl(String activateUrl) {
            this.activateUrl = activateUrl;
            return this;
        }

        public Builder customCell(String name, String tips, String url) {
            this.customCell = new CustomCell(name, tips, url);
            return this;
        }

        public Builder bonusRule(BonusRule bonusRule) {
            this.bonusRule = bonusRule;
            return this;
        }

        public Builder disCount(Integer discount) {
            this.discount = discount;
            return this;
        }

        public Builder backgroundPicUrl(String backgroundPicUrl) {
            this.backgroundPicUrl = backgroundPicUrl;
            return this;
        }

        public MemberCard build() {
            return new MemberCard(this);
        }
    }
}
