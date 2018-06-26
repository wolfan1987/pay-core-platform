package org.andrew.commons.wechat.model.card;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.andrew.commons.wechat.constants.CodeTypeEnum;


import java.io.Serializable;
import java.util.List;

/**
 * 基本的卡券数据，所有卡券通用。
 *
 * @author andrewliu
 */
public class BaseInfo extends OperationInfo implements Serializable {

    public BaseInfo() {}

    /**
     * baseInfo构造器。
     *
     * @param builder Builder
     */
    public BaseInfo(Builder builder) {
        this.logoUrl = builder.logoUrl;
        this.codeType = builder.codeType;
        this.brandName = builder.brandName;
        this.title = builder.title;
        this.color = builder.color;
        this.notice = builder.notice;
        this.description = builder.description;
        this.sku = builder.sku;
        this.dateInfo = builder.dateInfo;
        this.setServicePhone(builder.servicePhone);
        this.setGetLimit(builder.getLimit);
        this.setUseCustomCode(builder.useCustomCode);
        this.setCanGiveFriend(builder.canGiveFriend);
        this.setLocationIdList(builder.locationIdList);
        this.setCustomUrlName(builder.customUrlName);
        this.setCustomUrl(builder.customUrl);
        this.setCustomUrlSubTitle(builder.customUrlSubTitle);
        this.setPromotionUrlName(builder.promotionUrlName);
        this.setPromotionUrl(builder.promotionUrl);
        this.setNeedPushOnView(builder.needPushOnView);
        this.setCenterTitle(builder.centerTitle);
        this.setCenterUrl(builder.centerUrl);
        this.setCenterSubTitle(builder.centerSubTitle);
        this.setBindOpenid(builder.bindOpenId);
    }

    /**
     * 卡券的商户logo，建议像素为300*300。
     */
    @JSONField(name = "logo_url")
    private String logoUrl;

    /**
     * 会员卡类型。
     */
    @JSONField(name = "code_type")
    private CodeTypeEnum codeType;

    /**
     * 商户名字,字数上限为12个汉字。
     */
    @JSONField(name = "brand_name")
    private String brandName;

    /**
     * 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)
     */
    private String title;

    /**
     * 券颜色。按色彩规范标注填写Color010-Color100。
     */
    private String color;

    /**
     * 卡券使用提醒，字数上限为16个汉字。
     * 如：请出示二维码核销卡券
     */
    private String notice;

    /**
     * 卡券使用说明，字数上限为1024个汉字。
     */
    private String description;

    /**
     * 卡券信息。
     */
    @JSONField(name = "sku")
    private Sku sku;

    /**
     * 使用日期，有效期的信息。
     */
    @JSONField(name = "date_info")
    private DateInfo dateInfo;

    private String status;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public CodeTypeEnum getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeTypeEnum codeType) {
        this.codeType = codeType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public String toString() {
        return "BaseInfo{" + "logoUrl='" + logoUrl + '\'' + ", codeType=" + codeType +
               ", brandName='" + brandName + '\'' + ", title='" + title + '\'' + ", color='" +
               color + '\'' + ", notice='" + notice + '\'' + ", description='" + description +
               '\'' + ", sku=" + sku + ", dateInfo=" + dateInfo + ", status='" + status + '\'' +
               '}';
    }

    public static class Builder {
        private String       logoUrl;
        private CodeTypeEnum codeType;
        private String       brandName;
        private String       title;
        private String       color;
        private String       notice;
        private String       description;
        private Sku          sku;
        private DateInfo     dateInfo;

        //可选字段
        private String       servicePhone;
        private Integer      getLimit;
        private Boolean      useCustomCode;
        private Boolean      canGiveFriend;
        private List<String> locationIdList;
        private String       customUrlName;
        private String       customUrl;
        private String       customUrlSubTitle;
        private String       promotionUrlName;
        private String       promotionUrl;
        private Boolean      needPushOnView;
        private String       centerTitle;
        private String       centerUrl;
        private String       centerSubTitle;

        private Boolean bindOpenId;

        public Builder centerTitle(String centerTitle) {
            this.centerTitle = centerTitle;
            return this;
        }

        public Builder centerUrl(String centerUrl) {
            this.centerUrl = centerUrl;
            return this;
        }

        public Builder centerSubTitle(String centerSubTitle) {
            this.centerSubTitle = centerSubTitle;
            return this;
        }

        public Builder customUrlName(String customUrlName) {
            this.customUrlName = customUrlName;
            return this;
        }

        public Builder customUrl(String customUrl) {
            this.customUrl = customUrl;
            return this;
        }

        public Builder customUrlSubTitle(String customUrlSubTitle) {
            this.customUrlSubTitle = customUrlSubTitle;
            return this;
        }

        public Builder promotionUrlName(String promotionUrlName) {
            this.promotionUrlName = promotionUrlName;
            return this;
        }

        public Builder promotionUrl(String promotionUrl) {
            this.promotionUrl = promotionUrl;
            return this;
        }

        public Builder needPushOnView(Boolean needPushOnView) {
            this.needPushOnView = needPushOnView;
            return this;
        }

        public Builder(CodeTypeEnum codeType) {
            this.codeType = codeType;
        }

        public Builder getLimit(Integer getLimit) {
            this.getLimit = getLimit;
            return this;
        }

        public Builder logoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder brandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder notice(String notice) {
            this.notice = notice;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * sku。
         *
         * @param quantity 。
         * @return 返回构造体
         */
        public Builder sku(Integer quantity) {
            this.sku = new Sku();
            this.sku.setQuantity(quantity);
            return this;
        }

        public Builder dateInfo(DateInfo dateInfo) {
            this.dateInfo = dateInfo;
            return this;
        }

        public Builder servicePhone(String servicePhone) {
            this.servicePhone = servicePhone;
            return this;
        }

        public Builder useCustomCode(Boolean useCustomCode) {
            this.useCustomCode = useCustomCode;
            return this;
        }

        public Builder canGiveFriend(Boolean canGiveFriend) {
            this.canGiveFriend = canGiveFriend;
            return this;
        }

        public Builder locationIdList(List<String> locationIdList) {
            this.locationIdList = locationIdList;
            return this;
        }

        public Builder bindOpenId(Boolean bindOpenId) {
            this.bindOpenId = bindOpenId;
            return this;
        }

        public BaseInfo build() {
            return new BaseInfo(this);
        }
    }

}
