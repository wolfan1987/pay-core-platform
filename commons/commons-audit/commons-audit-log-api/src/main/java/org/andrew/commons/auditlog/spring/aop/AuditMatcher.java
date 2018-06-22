package org.andrew.commons.auditlog.spring.aop;


import org.andrew.commons.auditlog.annotation.Audit;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 审计日志匹配器。
 *
 * @author leaves chen on 2016/11/28
 */
public class AuditMatcher extends StaticMethodMatcher {
    private        AnnotationMethodMatcher methodMatcher = new AnnotationMethodMatcher(Audit.class);
    private static Map<Method, Boolean>    matchedCache  = new ConcurrentHashMap<>();

    public AuditMatcher() {}

    @Override
    public boolean matches(Method method, Class<?> auditClass) {
        if (matchedCache.containsKey(method)) {
            return matchedCache.get(method);
        } else {
            synchronized (matchedCache) {
                boolean match = methodMatcher.matches(method, auditClass);
                matchedCache.put(method, match);
                return match;
            }
        }
    }
}
