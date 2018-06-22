package org.andrew.commons.auditlog.impl.dao;



import org.andrew.commons.auditlog.api.dao.AuditLogDao;
import org.andrew.commons.auditlog.api.entitys.AuditLog;
import org.andrew.commons.auditlog.api.entitys.AuditLogVo;
import org.andrew.commons.auditlog.impl.dao.mapper.AuditMapper;
import org.andrew.commons.database.pagehelper.Page;
import org.andrew.commons.database.pagehelper.PageHelper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 审计日志接口实现类。
 * Created by leaves chen[leaves615@gmail.com] on 2016/12/8。
 *
 * @author leaves chen[leaves615@gmail.com]
 */
//@Service(version = "1.0.0")
public class AuditLogDaoImpl implements AuditLogDao {

    @Resource
    private AuditMapper auditMapper;

    public void save(
        String message, String target, Date createTime, int flag, String operator,
        String operatorId, String ip) {
        auditMapper.save(message, target, createTime, flag, operator, operatorId, ip);
    }

    @Override
    public Page<AuditLog> findByAuditLog(AuditLogVo auditLogVo, Page<AuditLog> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        auditMapper.findByAuditLog(auditLogVo);
        return PageHelper.endPage();
    }

    @Override
    public List<AuditLog> findByAuditLog(AuditLogVo auditLogVo) {
        return auditMapper.findByAuditLog(auditLogVo);
    }

    public void setAuditMapper(AuditMapper auditMapper) {
        this.auditMapper = auditMapper;
    }
}
