package org.andrew.commons.auditlog.impl.dao.mapper;


import org.andrew.commons.auditlog.api.entitys.AuditLog;
import org.andrew.commons.auditlog.api.entitys.AuditLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 审计日志数据接口。
 * Created by leaves chen[leaves615@gmail.com] on 2016/12/8。
 *
 * @author leaves chen[leaves615@gmail.com]
 */
public interface AuditMapper {

    void save(
            @Param("message") String message, @Param("target") String target,
            @Param("createTime") Date createTime, @Param("flag") int flag,
            @Param("operator") String operator, @Param("operatorId") String operatorId,
            @Param("ip") String ip);

    List<AuditLog> findByAuditLog(AuditLogVo auditLogVo);
}
