package org.andrew.commons.auditlog.spring.aop;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 审计日志切面点。
 *
 * @author leaves chen on 2016/11/28
 */
public class AuditPointcut extends StaticMethodMatcherPointcut {
    private MethodMatcher matcher;

    public AuditPointcut(MethodMatcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Method method, Class<?> auditClass) {
        return matcher.matches(method, auditClass);
    }
}
