package org.andrew.commons.auditlog.impl.service;



import org.andrew.commons.auditlog.api.dao.AuditLogDao;
import org.andrew.commons.auditlog.api.entitys.AuditLog;
import org.andrew.commons.auditlog.api.service.AuditLogService;

import java.util.Date;

/**
 * 审计日志业务实现类。
 * Created by leaves chen[leaves615@gmail.com] on 2017/10/13。
 *
 * @author leaves chen[leaves615@gmail.com]
 */
//@Service(version = "1.0.0")
public class AuditServiceImpl implements AuditLogService {
  //  @Reference(version = "1.0.0")
    private AuditLogDao auditDao;

    @Override
    public void succeed(AuditLog auditLog) {
        auditDao.save(auditLog.getMessage(),
                      makeTargetString(auditLog.getMethod(), auditLog.getAuditClass()), new Date(),
                      1, auditLog.getOperator(), auditLog.getOperatorId(), auditLog.getIp());
    }

    @Override
    public void fail(AuditLog auditLog) {
        auditDao.save(auditLog.getMessage(),
                      makeTargetString(auditLog.getMethod(), auditLog.getAuditClass()), new Date(),
                      0, auditLog.getOperator(), auditLog.getOperatorId(), auditLog.getIp());
    }

    private String makeTargetString(String method, String auditClass) {
        return auditClass + "#" + method;
    }
}
