package org.andrew.commons.web.constant;

/**
 * 系统定义常量。
 *
 * @author
 */
public enum SysAppEnum {

    ECARDAPP("ecardApp", "电子卡系统"), CUSTECARDAPP("custEcardApp", "大客户电子卡系统");

    private String code;

    private String desn;

    SysAppEnum(String code, String desn) {
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
