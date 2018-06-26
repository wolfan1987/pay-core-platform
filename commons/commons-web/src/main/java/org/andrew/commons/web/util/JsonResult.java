package org.andrew.commons.web.util;

/**
 * Created by andrewliu on 2017/3/23。
 */
public class JsonResult {

    private static final String SUCCESS = "success";

    private static final String FAIL = "fail";

    private String code;

    private String message;

    private Object data;

    public JsonResult() {

    }

    public JsonResult(String code, String message) {
        this(code, message, null);
    }

    /**
     * JSONResult。
     */
    public JsonResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static JsonResult success(Object data) {
        return new JsonResult(SUCCESS, "ok", data);
    }

    public static JsonResult success() {
        return new JsonResult(SUCCESS, "ok", null);
    }

    public static JsonResult error() {
        return new JsonResult(FAIL, "fail");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
