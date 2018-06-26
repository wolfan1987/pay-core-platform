package org.andrew.commons.ecard.common;

import org.apache.commons.lang.RandomStringUtils;


/**
 * 加解密工具类。
 *
 * @author andrewliu
 */
public class SecurityUtil {

    /**
     * 输出字符的ASC码。
     */
    private static int getAsc(char c) {
        return (int) (c);
    }

    /**
     * 字符串DES+Caesar加密。
     * 1.通过DES加密字符串
     * 2.随机产生一个字符，当作移位符，将加密串移位处理
     * 3.将字符和移位后的密串拼接，当作最终密文返回
     *
     * @param str 待加密串
     * @param key 加密密钥
     * @return 加密串
     */
    public static String encryptionDesCaesar(String str, String key) {
        // 1.通过des加密str
        String ch = Des.encrypt(str, key);

        // 2.生成随机字母，用于加密串移位处理

        String random = RandomStringUtils.randomAlphabetic(1).toUpperCase();
        int move = SecurityUtil.getAsc(random.charAt(0));
        String moveString = Caesar.encryption(ch, move);

        // 3.将移位符号拼接到加密串
        String encryptionString = random + moveString;

        return encryptionString;
    }

    /**
     * 密串DES+Caesar解密。
     * 1.将移位字符和密串分离
     * 2.按移位符将分离后密串移位处理
     * 3.通过DES解密字符串
     *
     * @param key 解密密钥
     * @return 揭秘后字符串
     */
    public static String decryptDesCaesar(String encryptionStr, String key) {
        // 1.分离移位字符和移位密串
        char decryptMoveChar = encryptionStr.charAt(0);
        int decryptMoveInt = SecurityUtil.getAsc(decryptMoveChar);
        String decryptMoveString = encryptionStr.substring(1);


        // 2.移位还原
        String decryptString = Caesar.decrypt(decryptMoveString, decryptMoveInt);

        // 3.最终解密
        return Des.decrypt(decryptString, key);
    }

    /**
     * 测试。
     */
    public static void main(String[] args) throws Exception {
        String openid = "ovzGcs893-TN11u-O0i4zKC9iZCM  ";
        String key = "8oHJKNLL";

        String encryption = SecurityUtil.encryptionDesCaesar(openid, key);
        String decrypt = decryptDesCaesar(encryption, key);


        System.out.println("加密串：" + encryption);
        System.out.println("解密串：" + decrypt);


    }
}