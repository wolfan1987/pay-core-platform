package org.andrew.commons.auditlog.provider;

import java.util.Collections;
import java.util.Map;

/**
 * 操作提供器实现。
 *
 * @author leaves chen  on 2016/12/8
 */
public class NoneOperatorProvider implements OperatorProvider {
    @Override
    public String getOperator() {
        return null;
    }

    @Override
    public String getOperatorId() {
        return null;
    }

    @Override
    public String getIp() {
        return null;
    }

    @Override
    public Map<String, Object> getCustomParam() {
        return Collections.emptyMap();
    }
}
