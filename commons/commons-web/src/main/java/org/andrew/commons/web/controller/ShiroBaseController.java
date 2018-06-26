package org.andrew.commons.web.controller;


import org.andrew.commons.web.shiro.RetryLimitCredentialsMatcher;
import org.andrew.commons.web.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 封装底层controller。
 * Created by andrewliu on 2018/3/22.
 */
public class ShiroBaseController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ShiroBaseController.class);

    @Autowired
    private RetryLimitCredentialsMatcher retryLimitCredentialsMatcher;

    /**
     * 获取当前登录用户对象。
     *
     * @return ShiroUser 当前登陆的对象
     */
    public ShiroUser getCurrentUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户会话。
     *
     * @return session
     */
    public Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取当前用户userId。
     *
     * @return 当前登陆对象的用户id
     */
    public String getUserId() {
        return this.getCurrentUser().getUserId();
    }

    /**
     * 获取当前用户所属机构。
     *
     * @return 当前登陆对象的用户id
     */
    public String getInstId() {
        return this.getCurrentUser().getInstId();
    }

    /**
     * 获取当前用户名称。
     *
     * @return 当前登陆对象的用户id
     */
    public String getUserName() {
        return this.getCurrentUser().getUserName();
    }

    /**
     * 获取当前用户sysUserId。
     *
     * @return 当前登陆对象的用户sys_user_id
     */
    public String getSysUserId() {
        return this.getCurrentUser().getSysUserId();
    }

    /**
     * 获取当前用户openId。
     *
     * @return 当前登陆对象的openId。
     */
    public String getOpenId() {
        return this.getCurrentUser().getOpenId();
    }

    /**
     * 获取当前用户unionId。
     *
     * @return 当前登陆对象的UnionId
     */
    public String getUnionId() {
        return this.getCurrentUser().getUnionId();
    }

    /**
     * shiro登录。
     * @return  结果
     */
    public Map<String, Object> login(
        String userAccount, String newPass, String captcha, Integer rememberMe) {

        UsernamePasswordToken token = new UsernamePasswordToken(userAccount, newPass, captcha);
        Subject currentUser = SecurityUtils.getSubject();
        int code = 1;
        String msg = "";
        try {
            //记住密码
            token.setRememberMe(1 == rememberMe);
            currentUser.login(token);
            msg = "登陆成功";
            code = 1;
        } catch (IncorrectCredentialsException ex) {
            code = 7;
            AtomicInteger atomicInteger = retryLimitCredentialsMatcher.getPasswordRetryCache().get(
                userAccount);
            int error = atomicInteger.get();
            int count = retryLimitCredentialsMatcher.getPasswordFailNumber();
            if (error == count) {
                msg = "登录密码错误" + error + "次,账户将锁定半小时";
            } else {
                msg = "登录密码错误" + error + "次," + count + "次错误账号将锁定半小时,请谨慎";
            }
            logger.error(msg, ex);
        } catch (ExcessiveAttemptsException ex) {
            code = 8;
            msg = "登录密码已错误" + retryLimitCredentialsMatcher.getPasswordFailNumber() + "次，账号已锁定半小时";
            logger.error(msg, ex);
        } catch (LockedAccountException ex) {
            code = 9;
            msg = "帐号已被锁定";
            logger.error(msg, ex);
        } catch (DisabledAccountException ex) {
            code = 10;
            msg = "帐号已被禁用";
            logger.error(msg, ex);
        } catch (ExpiredCredentialsException ex) {
            code = 11;
            msg = "帐号已过期";
            logger.error(msg, ex);
        } catch (UnknownAccountException ex) {
            code = 12;
            msg = "帐号不存在";
            logger.error(msg, ex);
        } catch (UnauthorizedException ex) {
            code = 13;
            msg = "该账号未分配角色，请先联系管理员分配角色";
            logger.error(msg, ex);
        } catch (AuthenticationException ex) {
            code = 14;
            msg = ex.getMessage();
            logger.error(msg, ex);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        return map;

    }


}
