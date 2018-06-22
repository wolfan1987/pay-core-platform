package org.andrew.commons.auditlog.impl.mq;

import com.alibaba.fastjson.JSON;

import org.andrew.commons.auditlog.api.entitys.AuditLog;
import org.andrew.commons.auditlog.api.service.AuditLogService;
import org.andrew.commons.mqoper.rkt.executes.consumer.AbstractPushConsumer;
import org.andrew.commons.mqoper.rkt.executes.consumer.ConsumerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 审计日志MQ消费消费者。
 * Created by leaves chen[leaves615@gmail.com] on 2017/10/17.
 *
 * @author leaves chen[leaves615@gmail.com]
 */
public class AuditLogConsumer extends AbstractPushConsumer {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogConsumer.class);

    @Resource
    private AuditLogService auditLogService;

    @Override
    public boolean execute(ConsumerMessage message) {
        if (logger.isDebugEnabled()) {
            logger.debug("接受审计日志消息：" + message.getBody());
        }
        AuditLog auditLog = JSON.parseObject(message.getBody(), AuditLog.class);
        if (auditLog.isSuccess()) {
            auditLogService.succeed(auditLog);
        } else {
            auditLogService.fail(auditLog);
        }
        return true;
    }
}
