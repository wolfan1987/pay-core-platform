package org.andrew.commons.auditlog.provider;

import java.util.Map;

/**
 * 审计日志操作人员信息获取。
 *
 * @author leaves chen on 2017/10/13
 */
public class ThreadLocalOperatorProvider implements OperatorProvider {
    @Override
    public String getOperator() {
        return ThreadOperatorContext.getOperator();
    }

    @Override
    public String getOperatorId() {
        return ThreadOperatorContext.getOperatorId();
    }

    @Override
    public String getIp() {
        return ThreadOperatorContext.getIp();
    }

    @Override
    public Map<String, Object> getCustomParam() {
        return ThreadOperatorContext.getParams();
    }


}
