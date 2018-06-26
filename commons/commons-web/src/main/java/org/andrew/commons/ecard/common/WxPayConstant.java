package org.andrew.commons.ecard.common;

import cn.expresspay.pay.util.PayProperties;

/**
 * 微信支付相关，不变的部分配置。
 * 采用微信支付
 *
 * @author andrewliu
 */
public class WxPayConstant {

    public static final String VERSION;

    public static final String PARTNER_CODE;

    public static final String CHARSET;

    public static final String CURRENCY;

    public static final String ACCOUNT_NAME;

    public static final String RETURN_URL;

    public static final String NOTIFY_URL;

    public static final String SIGN_TYPE;

    public static final String RECHARGE_RETURN_URL;

    public static final String RECHARGE_NOTIFY;

    public static final String EXTEND1;

    public static final String RECHARGE_EXTEND1;

    public static final Integer TIME_OUT;

    static {
        PARTNER_CODE = PayProperties.getProperty("partnerCode");
        VERSION = PayProperties.getProperty("version");
        CHARSET = PayProperties.getProperty("charset");
        CURRENCY = PayProperties.getProperty("currency");
        ACCOUNT_NAME = PayProperties.getProperty(PARTNER_CODE + "_payAccountName");
        RETURN_URL = PayProperties.getProperty("returnUrl");
        NOTIFY_URL = PayProperties.getProperty("notifyUrl");
        SIGN_TYPE = PayProperties.getProperty("signType");
        RECHARGE_RETURN_URL = PayProperties.getProperty("rechargeReturnUrl");
        RECHARGE_NOTIFY = PayProperties.getProperty("rechargeNotify");
        EXTEND1 = PayProperties.getProperty("buy_extend1");
        RECHARGE_EXTEND1 = PayProperties.getProperty("recharge_extend1");
        if (PayProperties.getProperty("timeOut") != null &&
            !"".equals(PayProperties.getProperty("timeOut"))) {
            TIME_OUT = Integer.parseInt(PayProperties.getProperty("timeOut"));
        } else {
            TIME_OUT = null;
        }
    }
}
