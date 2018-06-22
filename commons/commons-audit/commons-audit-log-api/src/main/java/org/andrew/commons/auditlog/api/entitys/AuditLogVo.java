package org.andrew.commons.auditlog.api.entitys;

/**
 * 审计日志VO类。
 *
 * @author maqiang on 2017/10/18.
 */
public class AuditLogVo extends AuditLog {

    /**
     * 创建日期-开始日期。
     */
    private String createTimeBegin;
    /**
     * 创建日期-结束日期。
     */
    private String createTimeEnd;

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
