package org.andrew.commons.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 防止重复提交拦截器。
 * Created by andrewliu on 2017/6/19.
 */
public class FormTokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(FormTokenInterceptor.class);
    /**
     * 页面token变量。
     */
    private static final String token  = "token";

    /**
     * 在执行controller前拦截。
     *
     * @param request  请求
     * @param response 响应
     * @param handler  handler
     * @return 是否继续执行
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(
        HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        logger.warn("please don't repeat submit,url:{}", request.getServletPath());
                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().write("请不要重复提交");
                        return false;
                    }
                    request.getSession(false).removeAttribute(token);
                }
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession(false).setAttribute(token, UUID.randomUUID().toString());
                    request.setAttribute(token, request.getSession(false).getAttribute(token));
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 检查是否重复提交。
     *
     * @param request 请求
     * @return true:重复提交  false:非重复提交
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute(token);
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter(token);
        return clientToken == null || !serverToken.equals(clientToken);
    }
}
