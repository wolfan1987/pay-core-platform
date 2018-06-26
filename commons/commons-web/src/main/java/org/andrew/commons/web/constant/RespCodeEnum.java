package org.andrew.commons.web.constant;

/**
 * 电子卡外部系统接口通用返回码枚举类型。
 *
 * @author andrewliu on 2018/4/8。
 */
public enum RespCodeEnum {

    SUCCESS("00", "操作成功"),

    USER_EXIST("01", "用户已存在"),

    USER_NOT_EXIST("02", "用户不存在"),

    USER_IS_LOCK("03", "用户已锁定"),

    NO_NEED_TO_HANDLE("04", "无需此操作"),

    DO_NOT_HANDLE("05", "不能此操作"),

    REPETITIVE_OPERATION("06", "不能重复此操作"),

    PASSWORD_NOT_SET("07", "支付密码未设置"),

    PASSWORD_ERROR("08", "支付密码错误"),

    PASSWORD_UN_FORMAT("09", "支付密码必须为6位数字"),

    TRAN_TYPE_ERROR("10", "交易类型错误"),

    LIMIT_ERROR("11", "额度错误"),

    NO_REAL_NAME("12", "用户未实名不能开此产品子类型卡"),

    REQUEST_PARAM_ERROR("13", "请求参数错误"),

    CARD_ALREADY_EXIST("14", "卡已存在"),

    CARD_NOT_EXIST("15", "卡不存在"),

    ORDER_ID_IS_USED("16", "渠道订单号已使用"),

    OUT_CARD_MISS("17", "转出卡不存在"),

    IN_CARD_MISS("18", "转入卡不存在"),

    BALANCE_SUFFICIENT("19", "余额不足"),

    BALANCE_OVER_FLOW("20", "余额超出上限"),

    NONSUPPORT_IN_TRANSFER("21", "此卡不支持转账(入)"),

    NONSUPPORT_OUT_TRANSFER("22", "此卡不支持转账(出)"),

    NONSUPPORT_ACCOUNT("23", "此卡不支持调账"),

    NONSUPPORT_RECHARGE("24", "此卡不支持充值"),

    NONSUPPORT_CLOSE_PAY("25", "此卡不支持止付"),

    NONSUPPORT_OPEN_PAY("26", "此卡不支持解止付"),

    NONSUPPORT_FREEZE("27", "此卡不支持冻结"),

    NONSUPPORT_UNFREEZE("28", "此卡不支持解冻"),

    NONSUPPORT_DELETE_CARD("29", "此卡不支持销卡"),

    NONSUPPORT_OPEN_CARD("30", "产品子类型不支持开卡"),

    PRODUCT_TYPE_ERROR("31", "产品子类型错误"),

    CARD_UN_ACTIVE("32", "卡未激活"),

    CARD_EXCEPTION("33", "异常卡"),

    CARD_USER_ERROR("34", "非此用户卡"),

    NONSUPPORT_PAY("35", "此卡不支持付款"),

    ORDER_NOT_FOUND("36", "订单不存在"),

    USER_CERTIFICATION("97", "用户已实名不能更新姓名和身份证号码，请使用实名认证接口更新"),

    FAIL("98", "操作失败"),

    CONNECT_FAIL("99", "网络异常");

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
    RespCodeEnum(String code, String desn) {
        this.code = code;
        this.desn = desn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesn() {
        return desn;
    }

    public void setDesn(String desn) {
        this.desn = desn;
    }
}
