package org.andrew.commons.ecard.common;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
    /**
     * 对字符串进行32位 md5加密，返回小写字符串。
     *
     */
    public static String encrypByMd5(String context) {
        String md5Str = DigestUtils.md5Hex(context);
        return md5Str;
    }

    /**
     * 测试。
     */
    public static void main(String[] args) {
        String sign = encrypByMd5(
            "8JWPL71vr98Wv11Azm7qk82D3t88cYtqaccessTokenc4ea5e1b1e0659ba97" +
            "a5a84536f63b58timeStamp1234568JWPL71vr98Wv11Azm7qk82D3t88cYtq");
        System.out.println("32result: " + sign);
    }
}
