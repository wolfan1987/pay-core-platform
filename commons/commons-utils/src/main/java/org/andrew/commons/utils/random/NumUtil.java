package org.andrew.commons.utils.random;

import java.util.Random;

/**
 * 数字生成工具。
 *
 * @author andrewliu
 */
public class NumUtil {

    /**
     * 生成指定位数纯数字随机数。
     *
     * @param num 数字位数。
     * @return 随机数
     */
    public static String generateSystrace(int num) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            sb.append(array[random.nextInt(array.length)]);
        }
        return String.valueOf(sb.toString());
    }
}
