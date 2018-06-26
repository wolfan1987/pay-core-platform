package org.andrew.commons.wechat.vo;



import org.andrew.commons.wechat.model.card.DateInfo;

import java.io.Serializable;

/**
 * 创建卡所需要的参数。
 *
 * @author andrewliu
 */
public class CardCreateReqVo implements Serializable {

    /**
     * 会员卡名称。
     */
    private String cardTitle;
    /**
     * 商户名称。
     */
    private String brandName;
    /**
     * 客服电话。
     */
    private String servicePhone;
    /**
     * 自定义入口名称。
     */
    private String customUrlName;
    /**
     * 自定义跳转。
     */
    private String customUrl;
    /**
     * 自定义提示。
     */
    private String customUrlSubTitle;
    /**
     * 使用场景入口名称。
     */
    private String centerName;
    /**
     * 使用场景入口url。
     */
    private String centerUrl;
    /**
     * 使用场景入口Subtitle。
     */
    private String centerSubTitle;
    /**
     * 营销入口名称。
     **/
    private String promotionUrlName;
    /**
     * 营销入口跳转的url。
     */
    private String promotionUrl;
    /**
     * 显示在营销入口右侧的提示语。
     */
    private String promotionUrlSubTitle;
    /**
     * 会员卡类型。
     */
    private String cardType;
    /**
     * 会员卡logo。
     */
    private String logoUrl;

    /**
     * 卡面设计请遵循微信会员卡自定义背景设计规范,像素大小控制在
     * 1000像素*600像素以下。
     */
    private String  backgroundPicUrl;
    /**
     * 会员卡颜色。
     */
    private String  color;
    /**
     * 卡券使用提醒，字数上限为16个汉字。
     */
    private String  notice;
    /**
     * 卡券使用说明，字数上限为1024个汉字。
     */
    private String  description;
    /**
     * 卡券库存的数量，不支持填写0，上限为100000000。
     */
    private Integer     quantity;
    /**
     * 填写true为用户点击进入会员卡时推送事件，默认为false。
     */
    private Boolean needPushOnView;
    /**
     * 自动激活，激活链接activate_url和一键开卡接口设置都会失效。
     */
    private Boolean autoActivate;
    /**
     * 一键开卡,若同时传入了activate_url，则一键开卡接口设置会失效。
     */
    private String  activateUrl;
    /**
     * 会员卡专属字段，表示特权说明。
     */
    private String  prerogative;
    /**
     * 自定义会员信息类目，会员卡激活后显示。入口名称。
     */
    private String  customCellName;
    /**
     * 自定义会员信息类目，会员卡激活后显示。提示。
     */
    private String  customCellTips;
    /**
     * 自定义会员信息类目，会员卡激活后显示。url。
     */
    private String  customCellUrl;
    /**
     * 是否使用自定义code。
     */
    private Boolean useCustomCode;
    /**
     * 可领张数。
     */
    private Integer limit    = 999999;
    /**
     * 折扣。
     */
    private Integer discount = 0;

    /**
     * 是否指定用户领取。
     */
    private Boolean bindOpenId;
    /**
     * 是否可以分享给朋友。
     */
    private Boolean canGiveFriend;

    /**
     * 是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
     */
    private Boolean supplyBalance;

    /**
     * 设置跳转外链查看余额详情。
     * 仅适用于余额无法通过激活接口同步的情况下使用该字段。
     */
    private String balanceUrl;

    /**
     * 日期类型设置。
     */
    private DateInfo dateInfo;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getCustomUrlName() {
        return customUrlName;
    }

    public void setCustomUrlName(String customUrlName) {
        this.customUrlName = customUrlName;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public String getCustomUrlSubTitle() {
        return customUrlSubTitle;
    }

    public void setCustomUrlSubTitle(String customUrlSubTitle) {
        this.customUrlSubTitle = customUrlSubTitle;
    }

    public String getPromotionUrlName() {
        return promotionUrlName;
    }

    public void setPromotionUrlName(String promotionUrlName) {
        this.promotionUrlName = promotionUrlName;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean isNeedPushOnView() {
        return needPushOnView;
    }

    public void setNeedPushOnView(Boolean needPushOnView) {
        this.needPushOnView = needPushOnView;
    }

    public Boolean isAutoActivate() {
        return autoActivate;
    }

    public void setAutoActivate(Boolean autoActivate) {
        this.autoActivate = autoActivate;
    }

    public String getPrerogative() {
        return prerogative;
    }

    public void setPrerogative(String prerogative) {
        this.prerogative = prerogative;
    }

    public String getActivateUrl() {
        return activateUrl;
    }

    public void setActivateUrl(String activateUrl) {
        this.activateUrl = activateUrl;
    }

    public String getCustomCellName() {
        return customCellName;
    }

    public void setCustomCellName(String customCellName) {
        this.customCellName = customCellName;
    }

    public String getCustomCellTips() {
        return customCellTips;
    }

    public void setCustomCellTips(String customCellTips) {
        this.customCellTips = customCellTips;
    }

    public String getCustomCellUrl() {
        return customCellUrl;
    }

    public void setCustomCellUrl(String customCellUrl) {
        this.customCellUrl = customCellUrl;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterUrl() {
        return centerUrl;
    }

    public void setCenterUrl(String centerUrl) {
        this.centerUrl = centerUrl;
    }

    public String getCenterSubTitle() {
        return centerSubTitle;
    }

    public void setCenterSubTitle(String centerSubTitle) {
        this.centerSubTitle = centerSubTitle;
    }

    public Boolean isUseCustomCode() {
        return useCustomCode;
    }

    public void setUseCustomCode(Boolean useCustomCode) {
        this.useCustomCode = useCustomCode;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getBalanceUrl() {
        return balanceUrl;
    }

    public void setBalanceUrl(String balanceUrl) {
        this.balanceUrl = balanceUrl;
    }

    public Boolean isBindOpenId() {
        return bindOpenId;
    }

    public void setBindOpenId(Boolean bindOpenId) {
        this.bindOpenId = bindOpenId;
    }

    public Boolean isCanGiveFriend() {
        return canGiveFriend;
    }

    public void setCanGiveFriend(Boolean canGiveFriend) {
        this.canGiveFriend = canGiveFriend;
    }

    public String getBackgroundPicUrl() {
        return backgroundPicUrl;
    }

    public void setBackgroundPicUrl(String backgroundPicUrl) {
        this.backgroundPicUrl = backgroundPicUrl;
    }

    public Boolean isSupplyBalance() {
        return supplyBalance;
    }

    public void setSupplyBalance(Boolean supplyBalance) {
        this.supplyBalance = supplyBalance;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getPromotionUrlSubTitle() {
        return promotionUrlSubTitle;
    }

    public void setPromotionUrlSubTitle(String promotionUrlSubTitle) {
        this.promotionUrlSubTitle = promotionUrlSubTitle;
    }
}
