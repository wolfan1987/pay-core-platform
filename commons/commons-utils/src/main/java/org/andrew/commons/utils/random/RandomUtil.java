package org.andrew.commons.utils.random;

import java.util.Random;

/**
 * 生成随机数工具类。
 */
public class RandomUtil {

    private static final String base =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

    /**
     * 获取随机字符串。
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            stringBuffer.append(base.charAt(number));
        }
        return stringBuffer.toString();
    }


}
