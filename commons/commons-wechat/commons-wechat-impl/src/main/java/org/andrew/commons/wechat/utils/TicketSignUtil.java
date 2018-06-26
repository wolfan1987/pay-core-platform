package org.andrew.commons.wechat.utils;



import org.andrew.commons.wechat.exception.WechatException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 临时票据签名。
 *
 * @author andrewliu
 */
public final class TicketSignUtil {

    private TicketSignUtil() {
    }

    /**
     * 临时票据获取签名。
     */
    public static Map<String, String> sign(String jsapiTicket, String url) {
        Map<String, String> ret = new HashMap<>();
        String nonceStr = create_nonce_str();
        String timestamp = create_timestamp();
        String signSource = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
        String signMsg = String.format(signSource, jsapiTicket, nonceStr, timestamp, url);
        String signature;
        try {
            signature = sha1(signMsg);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException exception) {
            throw new WechatException("临时票据签名出错", exception);
        }
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        return ret;
    }

    /**
     * 卡券相关的签名方法
     * 将 api_ticket、app_id、location_id、timestamp、nonce_str、card_id、card_type的value值进行字符串的字典序排序。
     * 将所有参数字符串拼接成一个字符串进行sha1加密，得到cardSign。
     *
     * @param cardTicket 卡票据
     * @param cardId     微信卡号
     * @param code       卡号
     * @param openId     openId
     * @return 返回签名信息
     */
    public static Map<String, String> cardSign(
        String cardTicket, String cardId, String code, String openId) {
        String nonceStr = create_nonce_str();
        String timestamp = create_timestamp();
        List list = new ArrayList<String>();
        list.add(cardTicket);
        list.add(cardId);
        list.add(timestamp);
        list.add(nonceStr);
        list.add(code);
        list.add(openId);
        Collections.sort(list);
        StringBuilder sortedStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sortedStr.append(list.get(i));
        }
        String signMsg = sortedStr.toString();
        String signature;
        try {
            signature = sha1(signMsg);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new WechatException("卡券临时票据签名出错", ex);
        }
        Map<String, String> ret = new HashMap<>();
        ret.put("card_id", cardId);
        ret.put("api_ticket", cardTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        return ret;
    }

    private static String sha1(String signMsg)
        throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String signature;
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(signMsg.getBytes("UTF-8"));
        signature = byteToHex(crypt.digest());
        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
