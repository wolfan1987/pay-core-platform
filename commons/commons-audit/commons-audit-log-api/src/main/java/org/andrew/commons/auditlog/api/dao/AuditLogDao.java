package org.andrew.commons.auditlog.api.dao;



import org.andrew.commons.database.pagehelper.Page;
import org.andrew.commons.auditlog.api.entitys.AuditLog;
import org.andrew.commons.auditlog.api.entitys.AuditLogVo;

import java.util.Date;
import java.util.List;

/**
 * 审计日志接口。
 *
 * @author leaves chen
 */
public interface AuditLogDao {

    public void save(String message, String target, Date createTime, int flag, String operator,
            String operatorId, String ip);

    /**
     * 分页查询所有的审计日志。
     *
     * @param auditLogVo 查询对象
     * @param page       分页
     * @return 审计日志列表
     */
    public Page<AuditLog> findByAuditLog(AuditLogVo auditLogVo, Page<AuditLog> page);

    /**
     * 查询所有的审计日志。
     *
     * @param auditLogVo 查询对象
     * @return 审计日志列表
     */
    public List<AuditLog> findByAuditLog(AuditLogVo auditLogVo);

}
