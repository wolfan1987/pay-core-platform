package org.andrew.commons.web.exception;

;import org.andrew.commons.web.constant.RespCodeEnum;

/**
 * 业务处理异常。
 *
 * @author andrewliu
 */
public class GenericException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code = RespCodeEnum.FAIL.getCode();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GenericException(RespCodeEnum respCodeEnum) {
        super(respCodeEnum.getDesn());
        this.code = respCodeEnum.getCode();
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String code, String message) {
        super(message);
        this.code = code;
    }

    public GenericException(Throwable cause) {
        super(cause);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMsg() {
        return this.getMessage();
    }
}
