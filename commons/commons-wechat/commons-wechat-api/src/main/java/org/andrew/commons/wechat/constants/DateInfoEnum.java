package org.andrew.commons.wechat.constants;

/**
 * 使用时间的类型。
 *
 * @author andrewliu
 */
public enum DateInfoEnum {

    DATE_TYPE_FIX_TIME_RANGE("固定日期区间"),
    DATE_TYPE_FIX_TERM("固定时长-自领取后按天算"),
    DATE_TYPE_PERMANENT("永久有效");

    private String description;

    DateInfoEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
