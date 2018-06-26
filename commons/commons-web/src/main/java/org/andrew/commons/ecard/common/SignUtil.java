package org.andrew.commons.ecard.common;

import cn.expresspay.pay.util.Base64;
import cn.expresspay.pay.util.PayProperties;
import cn.expresspay.pay.util.SignatureUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付结果加解密工具类。
 *
 * @author andrewliu
 * @version 1.0.0
 * @createTime 2017年5月2日下午3:34:23
 */
public class SignUtil {
    private static final Logger   logger                          = LoggerFactory.getLogger(
        SignUtil.class);
    /**
     * 支付回调成功输出给网关。
     */
    public static final  String   NOTIFY_RESPONSE                 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>1</result>";
    /**
     * 支付回调返回结果参数。
     */
    private static final String[] PAYGATEWAY_SIGN_PARAMETERNAMES  = {"version", "charset", "currency", "partnerCode", "accountName", "orderNo", "orderTime", "amount", "signType", "returnUrl", "notifyUrl", "extend1", "extend2", "payTime", "payResultCode", "payResultDesc"};
    /**
     * 支付请求参数。
     */
    private static final String[] PAY_REQUEST_SIGN_PARAMETERNAMES = {"version", "charset", "currency", "partnerCode", "accountName", "orderNo", "orderTime", "amount", "signType", "returnUrl", "notifyUrl", "extend1", "extend2"};

    public static String[] getPgSignParametersNames() {
        return PAYGATEWAY_SIGN_PARAMETERNAMES;
    }

    public static String[] getPayRequestParameterNames() {
        return PAY_REQUEST_SIGN_PARAMETERNAMES;
    }

    /**
     * 签名请求参数。
     *
     * @param dataMap 请求参数map
     * @return 签名结果
     */
    public static final String signRequestData(Map<String, String> dataMap, Object payConfig) {
        String requestData = getPayRequestData(dataMap);
        String privateCrtFile = "";
        String password = "";
        if (payConfig == null) {
            //商户号信息文件
            privateCrtFile = PayProperties.getProperty(
                (String) dataMap.get("partnerCode") + "_privateKeyName");
            //私钥密码
            password = PayProperties.getProperty(
                (String) dataMap.get("partnerCode") + "_privateKeyPsw");
        } else {
            //商户号信息文件
          //  privateCrtFile = payConfig.getPrivateKeyName();
            //私钥密码
          //  password = payConfig.getPrivateKeyPsw();
        }

        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            String filePath = "/config/" + privateCrtFile;
            InputStream ksfis = SignatureUtil.class.getResourceAsStream(filePath);
            BufferedInputStream ksbufin = new BufferedInputStream(ksfis);
            char[] keyPwd = password.toCharArray();
            ks.load(ksbufin, keyPwd);
            ksbufin.close();
            Enumeration enum1 = ks.aliases();
            String keyAlias = null;
            if (enum1.hasMoreElements()) {
                keyAlias = (String) enum1.nextElement();
                //System.out.println("alias=[" + keyAlias + "]");
            }
            PrivateKey priK = (PrivateKey) ks.getKey(keyAlias, keyPwd);
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(priK);
            signature.update(requestData.getBytes("UTF-8"));
            return Base64.encodeBytes(signature.sign());
        } catch (Exception ex) {
            logger.error("请求参数签名失败：" + ex);
            throw new RuntimeException(
                "failed to sign request data, please check configuration", ex);
        }
    }

    /**
     * 验证响应参数签名是否正确。
     *
     * @param dataMap     响应参数map
     * @param signContent 签名结果
     * @return true:正确    false:错误
     */
    public static boolean verifySignature(Map<String, String> dataMap, String signContent) {
        String payResultData = getPayResultSignData(dataMap);
        try {
            //公钥
            String filePath = "/config/" + PayProperties.getProperty("pg_pub_key");
            InputStream inStream = SignatureUtil.class.getResourceAsStream(filePath);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
            PublicKey pk = cert.getPublicKey();
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(pk);
            signature.update(payResultData.getBytes("UTF-8"));
            byte[] verify = Base64.decode(signContent);
            return signature.verify(verify);
        } catch (Exception ex) {
            logger.error("响应参数签名失败：" + ex);
            throw new RuntimeException(
                "Verify paygate way response data failed, please check configuration", ex);
        }
    }

    /**
     * 根据响应参数的顺序组装成URI参数字符串。
     *
     * @param dataMap 响应参数map
     */
    public static String getPayResultSignData(Map<String, String> dataMap) {
        StringBuilder sb = new StringBuilder();
        for (String fieldName : getPgSignParametersNames()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(fieldName + "=" + nullToEmpty((String) dataMap.get(fieldName)));
        }
        return sb.toString();
    }

    /**
     * 根据请求参数的顺序组装成URI参数字符串。
     *
     * @param dataMap 请求参数map
     */
    public static String getPayRequestData(Map<String, String> dataMap) {
        StringBuilder sb = new StringBuilder();
        for (String fieldName : getPayRequestParameterNames()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(fieldName + "=" + (String) dataMap.get(fieldName));
        }
        return sb.toString();
    }

    /**
     * 获取响应参数。
     *
     * @param request 响应请求
     * @return 参数map
     */
    public static Map<String, String> getPayResultData(HttpServletRequest request) {
        HashMap<String, String> dataMap = new HashMap<String, String>();
        for (String parameterName : getPgSignParametersNames()) {
            String parameterValue = nullToEmpty(request.getParameter(parameterName));
            dataMap.put(parameterName, parameterValue);
            logger.info(parameterName + ":--" + parameterValue);
        }
        return dataMap;
    }

    /**
     * 支付网关支付请求拼接URI地址。
     *
     * @param dataMap 参数
     * @return URI地址
     */
    public static final String getPayRequestUrl(Map<String, String> dataMap) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key, "UTF-8")).append("=").append(
                    URLEncoder.encode(value, "UTF-8"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //配置文件支付请求地址
        String platFormUrl = PayProperties.getProperty("platform_pay_url");
        if ((platFormUrl == null) || (platFormUrl.trim().length() == 0)) {
            throw new RuntimeException(
                "pay platform url can not be empty,please check configuration");
        }
        return platFormUrl + "?" + sb.toString();
    }

    /**
     * 将null转成""。
     *
     * @param str null
     * @return ""
     */
    public static final String nullToEmpty(String str) {
        return str == null ? "" : str.trim();
    }
}