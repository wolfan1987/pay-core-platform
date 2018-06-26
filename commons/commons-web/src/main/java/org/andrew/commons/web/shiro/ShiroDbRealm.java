package org.andrew.commons.web.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.io.Serializable;
import java.util.List;

public class ShiroDbRealm{
//        extends CasRealm {
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("---------开始验证---------------");
//        try {
//            String username = (String) principals.getPrimaryPrincipal();
//            System.out.println("---------这个应该就是登陆进来的用户名---------------" + username);
//            SimpleAuthorizationInfo author = new SimpleAuthorizationInfo();
//            MenuControllerClientApi power = new MenuControllerClientApi();
//            List<String> sysMenus = power.getUserMenus(username);
//            //查询数据库的permission
//            author.addRole("role");
//            for (String permission : sysMenus) {
//                author.addStringPermission(permission);
//            }
//            return author;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static class ShiroUser implements Serializable {
//        private static final long serialVersionUID = -3041131129273959698L;
//        public long   id;
//        public String name;
//        public long   type;
//        public String account;
//    }

}
