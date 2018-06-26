package org.andrew.commons.web.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * session工具类。
 * Created by andrewliu on 2017/7/14.
 */
public class SessionUtil {
    /**
     * 保存登录名。
     */
    public static void setSession(Object key, Object value) {
        Session session = getSession();
        if (null != session) {
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            session.setAttribute(key, value);
        }
    }

    /**
     * 获取session。
     *
     * @return session信息
     */
    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
