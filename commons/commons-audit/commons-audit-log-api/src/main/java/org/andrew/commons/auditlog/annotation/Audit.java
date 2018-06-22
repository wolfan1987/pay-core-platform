package org.andrew.commons.auditlog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 审计注解
 * 描述审计日志内容,标记需要审计的方法。
 *
 * @author leaves chen on 2016/11/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {

    /**
     * 审计消息。
     * 支持spel表达式,内容变更包含
     * returnValue(返回值), params(参数列表), method(方法定义), class(类), exception(异常)。
     * spel 使用#{}包含表达式, 使用变量: #returnValue
     * 例如:  @Audit(message = "这是一条审计消息,执行结果是#{#returnValue}")
     *
     * @return 值
     */
    String value();
}
