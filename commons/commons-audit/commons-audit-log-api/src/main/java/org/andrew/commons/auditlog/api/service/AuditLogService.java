package org.andrew.commons.auditlog.api.service;


import org.andrew.commons.auditlog.api.entitys.AuditLog;

/**
 * 审计日志服务。
 * 实现此服务后,应使用 @ExportSerivce(serviceTypes = AuditLogService.class) 输入Massy 服务,
 * 否则审计日志切面程序无法发现
 *
 * @author leaves chen on 2016/11/28
 */
public interface AuditLogService {

    /**
     * 审计成功处理。
     *
     * @param auditLog 对象
     */
    void succeed(AuditLog auditLog);

    /**
     * 审计失败处理。
     *
     * @param auditLog 对象
     */
    void fail(AuditLog auditLog);
}
