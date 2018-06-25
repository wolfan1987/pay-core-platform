package org.andrew.commons.utils.order;

import java.util.UUID;

/**
 * 随机流水。
 * Created by lidan on 2017/6/27.
 */
public class SysTraceUtil {

    /**
     * 获取12位的唯一流水。
     *
     * @return 流水号
     */
    public static String getShortUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
    }
}
