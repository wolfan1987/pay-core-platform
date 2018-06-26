package org.andrew.commons.web.controller;


import org.apache.commons.lang3.StringUtils;

/**
 * 基础controller，提供一些基本的返回调用。
 *
 * @author andrewliu
 */
public class BaseController {

    /**
     * ajax失败。
     *
     * @param msg 失败的消息提示语
     * @return Object
     */
    public Object renderError(String msg) {
        JsonResult result = new JsonResult();
        result.setCode(JsonResult.FAIL);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax失败。
     *
     * @param code 错误码
     * @param msg  错误提示信息
     * @return JsonResult
     */
    public Object renderError(String code, String msg) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功。
     *
     * @return object
     */
    public Object renderSuccess() {
        JsonResult result = new JsonResult();
        result.setCode(JsonResult.SUCCESS);
        return result;
    }

    /**
     * ajax成功。
     *
     * @param msg 成功的消息提示语
     * @return Object
     */
    public Object renderSuccess(String msg) {
        JsonResult result = new JsonResult();
        result.setCode(JsonResult.SUCCESS);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功。
     *
     * @param obj 成功对象
     * @return Object
     */
    public Object renderSuccess(Object obj) {
        JsonResult result = new JsonResult();
        result.setCode(JsonResult.SUCCESS);
        result.setData(obj);
        return result;
    }

    /**
     * redirect跳转。
     *
     * @param url 目标url
     * @return spring mvc重定向格式
     */
    protected String redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * 获取导出最大值。
     *
     * @param sysArgsDao   系统参数查询Dao
     * @param sysMaxExport 系统参数
     * @return 返回最大导出值
     */
//    public Integer getMaxExport(SysArgsDao sysArgsDao, String sysMaxExport) {
//        SysArgs sysArgs = sysArgsDao.findByArgName(sysMaxExport);
//        if (sysArgs != null && StringUtils.isNotEmpty(sysArgs.getArgValue())) {
//            return Integer.parseInt(sysArgs.getArgValue());
//        }
//        return 10000;
//    }
}
