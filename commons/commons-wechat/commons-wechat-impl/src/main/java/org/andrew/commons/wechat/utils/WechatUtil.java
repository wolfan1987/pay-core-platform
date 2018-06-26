package org.andrew.commons.wechat.utils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 微信工具 验证消息等。
 *
 * @author andrewliu
 */
public final class WechatUtil {

    private WechatUtil() {}

    /**
     * 验证消息真实性 signature
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。timestamp 时间戳
     * nonce 随机数
     */
    public static boolean access(String token, String signature, String timestamp, String nonce)
        throws NoSuchAlgorithmException {
        // 存为List
        List<String> ss = new ArrayList<>();
        ss.add(timestamp);
        ss.add(nonce);
        ss.add(token);
        // 排序
        Collections.sort(ss);

        // 把数组元素组合为一个字符串。
        StringBuilder builder = new StringBuilder();
        for (String s : ss) {
            builder.append(s);
        }
        // 不考虑大小写，并且将字符串sha1加密
        return signature.equalsIgnoreCase(HashKit.sha1(builder.toString()));
    }
}
