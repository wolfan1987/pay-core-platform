package org.andrew.commons.auditlog.provider;

import java.util.Map;

/**
 * 操作人提供器。
 *
 * @author leaves chen on 2016/12/8
 */
public interface OperatorProvider {

    /**
     * 获取当前操作人。
     *
     * @return 操作者
     */
    String getOperator();

    /**
     * 获取当前操作人ID。
     *
     * @return 操作者ID
     */
    String getOperatorId();

    /**
     * 操作人ip。
     *
     * @return IP
     */
    String getIp();

    /**
     * 获取自定义参数。
     *
     * @return 集合
     */
    Map<String, Object> getCustomParam();


}
