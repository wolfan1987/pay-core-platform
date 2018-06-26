package org.andrew.commons.utils.random;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 批次号生成。
 *
 * @author andrewliu
 */
public class BatchNoUtil {

    /**
     * 生成批次号。
     *
     * @return String 批次号
     */
    public static String getBatchNo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String head = dateFormat.format(new Date());
        //生成两位随机数
        Random random = new Random();
        int tail = random.nextInt(90) + 10;
        return head + tail;
    }


}
