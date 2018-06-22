package org.andrew.commons.auditlog.spring.aop;


import org.andrew.commons.auditlog.annotation.Audit;
import org.andrew.commons.auditlog.api.entitys.AuditLog;
import org.andrew.commons.auditlog.provider.OperatorProvider;
import org.andrew.commons.mqoper.rkt.executes.producer.JsonMessageProducer;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 审计日志aop拦截器。
 *
 * @author leaves chen on 2016/11/28
 */
public class AuditInterceptor implements MethodInterceptor {
    private static Log logger = LogFactory.getLog(AuditInterceptor.class);

    private static Map<String, Expression> cachedExpressionMap = new HashMap<>();

    private static final Object cacheLock = new Object();

    @Autowired
    private JsonMessageProducer auditLogMessageProducer;

    private ExpressionParser expressionParser;

    private OperatorProvider operatorProvider;

    public AuditInterceptor() {
        expressionParser = new SpelExpressionParser();
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        boolean success = false;
        Exception exception = null;
        Object result = null;
        try {
            result = invocation.proceed();
            success = true;
            return result;
        } catch (Exception ex) {
            exception = ex;
            throw ex;
        } finally {
            //处理日志
            try {
                processAuditLog(invocation, success, result, exception);
            } catch (Exception ex) {
                if (logger.isDebugEnabled()) {
                    logger.error("处理审计日志失败", ex);
                }
            }
        }
    }

    private void processAuditLog(
        MethodInvocation invocation, boolean success, Object returnValue, Exception exception) {
        Class targetClass =
            invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null;
        Audit annotation = invocation.getMethod().getAnnotation(Audit.class);
        Object[] arguments = invocation.getArguments();
        Map<String, Object> variables = new HashMap<>();
        variables.put("returnValue", returnValue);
        variables.put("params", arguments);
        variables.put("method", invocation.getMethod());
        variables.put("class", targetClass);
        String targetClassName = targetClass != null ? targetClass.getCanonicalName() : null;
        AuditLog auditLog = new AuditLog(processMessage(annotation.value(), variables),
                                         invocation.getMethod().getName(), targetClassName,
                                         returnValue, exception, operatorProvider.getOperator(),
                                         operatorProvider.getOperatorId(), operatorProvider.getIp(),
                                         success, operatorProvider.getCustomParam());
        if (logger.isDebugEnabled()) {
            logger.debug("处理审计日志: " + auditLog);
        }
        auditLogMessageProducer.sendTextMsg(auditLog, Long.toString(System.nanoTime()));
    }


    private String processMessage(String expressionString, Map<String, Object> variables) {
        Expression expression = cachedExpressionMap.get(expressionString);
        if (expression == null) {
            synchronized (cacheLock) {
                expression = cachedExpressionMap.get(expressionString);
                if (expression == null) {
                    expression = expressionParser.parseExpression(
                        expressionString, ParserContext.TEMPLATE_EXPRESSION);
                    cachedExpressionMap.put(expressionString, expression);
                }
            }
        }
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariables(variables);
        return expression.getValue(evaluationContext, String.class);
    }


    /**
     * 测试main方法。
     *
     * @param args 系统配置
     */
    public static void main(String[] args) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("a", "1");
        variables.put("b", "2");
        String[] str = new String[2];
        str[0] = "a";
        str[1] = "b";
        variables.put("c", str);
        AuditInterceptor interceptor = new AuditInterceptor();
        System.out.println(interceptor.processMessage("sssss#{#a}sss#{#b}ssss#{#c[0]}", variables));
    }

    public OperatorProvider getOperatorProvider() {
        return operatorProvider;
    }

    public void setOperatorProvider(OperatorProvider operatorProvider) {
        this.operatorProvider = operatorProvider;
    }

    public void setAuditlogMessageProducer(
        JsonMessageProducer auditlogMessageProducer) {
        this.auditLogMessageProducer = auditlogMessageProducer;
    }
}
