package org.andrew.commons.web.controller;

/**
 * ajax返回结果。
 * @author andrewliu
 */
public class JsonResult {

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    private String code;

    private String msg;

    private Object data;

    public JsonResult() {

    }

    /**
     * 构造函数。
     * @param code code
     * @param message 提示语
     */
    public JsonResult(String code, String message) {
        this(code, message, null);
    }

    /**
     * 构造函数。
     * @param code code
     * @param msg 提示语
     * @param data 返回到前端的数据
     */
    public JsonResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
