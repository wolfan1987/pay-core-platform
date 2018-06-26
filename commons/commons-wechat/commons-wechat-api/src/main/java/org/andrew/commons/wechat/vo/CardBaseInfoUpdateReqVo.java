package org.andrew.commons.wechat.vo;



import org.andrew.commons.wechat.constants.CodeTypeEnum;
import org.andrew.commons.wechat.model.card.DateInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 会员卡更新字段信息。
 *
 * @author andrewliu
 */
public class CardBaseInfoUpdateReqVo implements Serializable {

    /**
     * 会员卡标题，字数上限为9个汉字。
     * 需要审核
     */
    private String title;

    /**
     * logoUrl。
     */
    private String logoUrl;

    /**
     * 使用提醒，字数上限为16个汉字。
     */
    private String notice;

    /**
     * 使用说明。
     * 需要审核。
     */
    private String description;

    /**
     * 客服电话。
     */
    private String servicePhone;

    /**
     * 卡券颜色。
     */
    private String color;

    /**
     * 支持更新适用门店列表。
     */
    private List<String> locationIdList;

    /**
     * 支持全部门店，填入后卡券门店跟随商户门店更新而更新。
     */
    private Boolean useAllLocations;

    /**
     * 会员卡中部的跳转按钮名称。
     */
    private String centerTitle;

    /**
     * 会员卡中部的跳转按钮名称，建议用作使用用途。
     */
    private String centerSubTitle;

    /**
     * 会员卡中部按钮对应跳转的url。
     */
    private String centerUrl;

    /**
     * 自定义跳转入口的名字。
     */
    private String customUrlName;

    /**
     * 自定义跳转的URL。
     */
    private String customUrl;

    /**
     * 显示在入口右侧的提示语。
     */
    private String customUrlSubTitle;

    /**
     * 营销场景的自定义入口名称。
     */
    private String promotionUrlName;

    /**
     * 入口跳转外链的地址链接。
     */
    private String promotionUrl;

    /**
     * 显示在营销入口右侧的提示语。
     */
    private String promotionUrlSubTitle;

    /**
     * Code码展示类型。
     */
    private CodeTypeEnum codeType;

    /**
     * 是否设置该会员卡支持拉出微信支付刷卡界面。
     */
    private Boolean swipeCard;

    /**
     * 是否设置该会员卡支持拉出微信支付刷卡界面。
     */
    private String isSwipeCard;

    /**
     * 是否设置该会员卡中部的按钮同时支持微信支付刷卡和会员卡二维码。
     */
    private Boolean isPayAndQrcode;

    /**
     * 每人可领券的数量限制。
     */
    private Integer getLimit;

    /**
     * 卡券原生领取页面是否可分享。
     */
    private Boolean canShare;

    /**
     * 卡券是否可转赠。
     */
    private Boolean canGiveFriend;

    /**
     * 使用日期，有效期的信息，有效期时间修改仅支持有效区间的扩大。
     */
    private DateInfo dateInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getLocationIdList() {
        return locationIdList;
    }

    public void setLocationIdList(List<String> locationIdList) {
        this.locationIdList = locationIdList;
    }

    public Boolean getUseAllLocations() {
        return useAllLocations;
    }

    public void setUseAllLocations(Boolean useAllLocations) {
        this.useAllLocations = useAllLocations;
    }

    public String getCenterTitle() {
        return centerTitle;
    }

    public void setCenterTitle(String centerTitle) {
        this.centerTitle = centerTitle;
    }

    public String getCenterSubTitle() {
        return centerSubTitle;
    }

    public void setCenterSubTitle(String centerSubTitle) {
        this.centerSubTitle = centerSubTitle;
    }

    public String getCenterUrl() {
        return centerUrl;
    }

    public void setCenterUrl(String centerUrl) {
        this.centerUrl = centerUrl;
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

    public String getPromotionUrlSubTitle() {
        return promotionUrlSubTitle;
    }

    public void setPromotionUrlSubTitle(String promotionUrlSubTitle) {
        this.promotionUrlSubTitle = promotionUrlSubTitle;
    }

    public CodeTypeEnum getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeTypeEnum codeType) {
        this.codeType = codeType;
    }

    public Boolean getSwipeCard() {
        return swipeCard;
    }

    public void setSwipeCard(Boolean swipeCard) {
        this.swipeCard = swipeCard;
    }

    public String getIsSwipeCard() {
        return isSwipeCard;
    }

    public void setIsSwipeCard(String isSwipeCard) {
        this.isSwipeCard = isSwipeCard;
    }

    public Boolean getPayAndQrcode() {
        return isPayAndQrcode;
    }

    public void setPayAndQrcode(Boolean payAndQrcode) {
        isPayAndQrcode = payAndQrcode;
    }

    public Integer getGetLimit() {
        return getLimit;
    }

    public void setGetLimit(Integer getLimit) {
        this.getLimit = getLimit;
    }

    public Boolean getCanShare() {
        return canShare;
    }

    public void setCanShare(Boolean canShare) {
        this.canShare = canShare;
    }

    public Boolean getCanGiveFriend() {
        return canGiveFriend;
    }

    public void setCanGiveFriend(Boolean canGiveFriend) {
        this.canGiveFriend = canGiveFriend;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }
}
