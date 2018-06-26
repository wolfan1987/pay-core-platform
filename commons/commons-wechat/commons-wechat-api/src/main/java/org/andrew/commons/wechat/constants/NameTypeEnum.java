package org.andrew.commons.wechat.constants;

/**
 * 会员信息类目名称。
 *
 * @author andrewliu
 */
public enum NameTypeEnum {

    FIELD_NAME_TYPE_LEVEL("等级"),
    FIELD_NAME_TYPE_COUPON("优惠券"),
    FIELD_NAME_TYPE_STAMP("印花"),
    FIELD_NAME_TYPE_DISCOUNT("折扣"),
    FIELD_NAME_TYPE_ACHIEVEMEN("成就"),
    FIELD_NAME_TYPE_MILEAGE("里程"),
    FIELD_NAME_TYPE_SET_POINTS("集点"),
    FIELD_NAME_TYPE_TIMS("次数");

    private String description;

    NameTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
