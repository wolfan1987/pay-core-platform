package org.andrew.commons.web.constant;

/**
 * 支付渠道状态码。
 *
 * @author andrewliu
 */
public enum ThirdPartyEnum {

    ALIPAYWAPPAY("alipayWapPay", "支付宝H5支付"), ALIPAYAPPPAY("alipayAppPay", "支付宝APP支付"), BANKCOMMPAY(
        "bankCommPay", "交通银行-WEB支付"), BANKCOMMCPAY("bankCommCPay", "交通银行-WEB支付(信用卡)"), BESTPAY(
        "bestPay", "翼支付-WEB渠道"), BESTWAPPAY("bestWapPay", "翼支付"), UNIONPAYPARTY(
        "UnionpayParty", "银联在线"), WXAPPPAY("wxAppPay", "微信App支付"), WXGZHPAY("wxGzhPay", "微信支付");

    private String code;

    private String name;

    ThirdPartyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * getNameByCode。
     */
    public static String getNameByCode(String codeVal) {
        for (ThirdPartyEnum thirdPartyEnum : ThirdPartyEnum.values()) {
            if (codeVal.equals(thirdPartyEnum.code)) {
                return thirdPartyEnum.name;
            }
        }
        return "";
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
