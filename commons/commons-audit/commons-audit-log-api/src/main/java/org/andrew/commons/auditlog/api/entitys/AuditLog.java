package org.andrew.commons.auditlog.api.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 审计日志JavaBean。
 *
 * @author leaves chen on 2016/11/28
 */
public class AuditLog implements Serializable {
    private String              id;
    private String              message;
    private String              target;
    private String              method;
    private String              auditClass;
    private Object              returnValue;
    private Exception           exception;
    private Date                createTime;
    private String              operator;
    private String              operatorId;
    private String              ip;
    private Integer             flag;//1成功 0失败
    private boolean             success;
    private Map<String, Object> param;

    public AuditLog() {
    }

    /**
     * 构造方法。
     *
     * @param message     消息
     * @param method      方法
     * @param auditClass  class
     * @param returnValue 返回值
     * @param exception   异常
     * @param operator    操作者
     * @param operatorId  操作id
     * @param ip          ip地址
     * @param success     是否成功
     * @param param       集合
     */
    public AuditLog(
        String message, String method, String auditClass, Object returnValue, Exception exception,
        String operator, String operatorId, String ip, boolean success, Map<String, Object> param) {
        this.message = message;
        this.method = method;
        this.auditClass = auditClass;
        this.returnValue = returnValue;
        this.exception = exception;
        this.operatorId = operatorId;
        this.ip = ip;
        this.success = success;
        this.param = param;
        this.createTime = new Date();
        this.operator = operator;
    }

    public String getMessage() {
        return message;
    }

    public String getMethod() {
        return method;
    }

    public String getAuditClass() {
        return auditClass;
    }

    public void setAuditClass(String auditClass) {
        this.auditClass = auditClass;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public Exception getException() {
        return exception;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
