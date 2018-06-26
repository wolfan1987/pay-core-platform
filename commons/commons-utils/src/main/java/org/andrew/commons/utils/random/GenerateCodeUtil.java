package org.andrew.commons.utils.random;

import java.util.Random;

/**
 * 生成短信验证码。
 * Created by andrewliu on 2017/7/18.
 */
public class GenerateCodeUtil {
    /**
     * 生成6位纯数字随机数。
     *
     * @return 6位纯数字随机数
     */
    public static String generateVerifyCode() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 6; i++) {
            result = result * 10 + array[i];
        }
        return String.valueOf(result);
    }
}
