package org.andrew.commons.auditlog.spring.aop;


import org.andrew.commons.auditlog.provider.NoneOperatorProvider;
import org.andrew.commons.auditlog.provider.OperatorProvider;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 审计日志Advisor。
 *
 * @author leaves chen on 2016/11/28
 */
public class AuditAdvisor extends AbstractPointcutAdvisor
    implements InitializingBean, ApplicationContextAware {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
        AuditAdvisor.class);
    private AuditPointcut      pointcut;
    private AuditInterceptor   interceptor;
    private OperatorProvider operatorProvider;
    private ApplicationContext applicationContext;


    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }


    @Override
    public Advice getAdvice() {
        return this.interceptor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (pointcut == null) {
            pointcut = new AuditPointcut(new AuditMatcher());
        }
        if (operatorProvider == null) {
            logger.warn("audit operatorProvider not set. use NoneOperatorProvider.");
            operatorProvider = new NoneOperatorProvider();
        }
        if (interceptor == null) {
            interceptor = new AuditInterceptor();
            interceptor.setOperatorProvider(operatorProvider);
        }
    }

    public void setPointcut(AuditPointcut pointcut) {
        this.pointcut = pointcut;
    }

    public AuditInterceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(AuditInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public OperatorProvider getOperatorProvider() {
        return operatorProvider;
    }

    public void setOperatorProvider(OperatorProvider operatorProvider) {
        this.operatorProvider = operatorProvider;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
