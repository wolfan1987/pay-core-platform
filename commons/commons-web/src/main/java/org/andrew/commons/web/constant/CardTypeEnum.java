package org.andrew.commons.web.constant;

/**
 * 卡类型常量。
 *
 * @author andrewliu 2018-06-15 16:00:00
 */
public enum CardTypeEnum {

    CARD_IN("IN", "转出卡"),

    CARD_OUT("OUT", "转入卡");

    /**
     * 返回码。
     */
    private String code;

    /**
     * 提示信息。
     */
    private String desn;

    /**
     * 构造方法。
     *
     * @param code 返回码
     * @param desn 提示信息
     */
    CardTypeEnum(String code, String desn) {
        this.code = code;
        this.desn = desn;
    }

    public String getCode() {
        return code;
    }

    public String getDesn() {
        return desn;
    }

}
