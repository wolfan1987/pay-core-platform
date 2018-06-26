package org.andrew.commons.web.interceptor;


import org.andrew.commons.auditlog.provider.ThreadOperatorContext;
import org.andrew.commons.web.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录后缓存用户信息。
 * Created by andrewliu on 2017/7/17.
 * modified by leaves chen on 2017/10/13
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(
        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj)
        throws Exception {
        ThreadContext.bind(SecurityUtils.getSubject());
        Subject subject = ThreadContext.getSubject();
        Subject user = SecurityUtils.getSubject();
        Object object = user.getPrincipal();
        if (null != object) {
            ShiroUser shiroUser = (ShiroUser) object;
            httpServletRequest.setAttribute("shiroUser", shiroUser.getUserName());
            //设置审计日志必要参数
            ThreadOperatorContext.setOperator(shiroUser.getUserName());
            ThreadOperatorContext.setOperatorId(shiroUser.getUserId());
            ThreadOperatorContext.setIp(getIpAddress(httpServletRequest));
        } else {
            httpServletRequest.setAttribute("shiroUser", "");
        }
        return true;
    }

    @Override
    public void postHandle(
        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(
        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj,
        Exception ex) throws Exception {

    }

    /**
     * 获取ip。
     */
    public static final String getIpAddress(HttpServletRequest request) {

        try {
            /****获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址。***/
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("X-Real-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            } else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (int index = 0; index < ips.length; index++) {
                    String strIp = (String) ips[index];
                    if (!("unknown".equalsIgnoreCase(strIp))) {
                        ip = strIp;
                        break;
                    }
                }
            }
            return ip;
        } catch (Exception ex) {
            logger.error("获取请求IP异常", ex);
            return null;
        }
    }
}
