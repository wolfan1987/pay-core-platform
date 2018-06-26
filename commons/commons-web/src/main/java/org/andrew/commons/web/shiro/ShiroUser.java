package org.andrew.commons.web.shiro;



import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息。
 *
 * @author andrewliu
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String userPassword;
    private String userAccount;
    private String userId;
    private String instId;
    private Set<String> roles;
  //  private Set<List<LeftMenu>> leftMenus;
    private Set<String> rights;
    private String openId;
    private String unionId;
    private String sysUserId;
    private String regInst;//注册机构
    private String regBarnch; //注册网点--渠道
    private String custNo;
    private String sysCode;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

//    public Set<List<LeftMenu>> getLeftMenus() {
//        return leftMenus;
//    }
//
//    public void setLeftMenus(Set<List<LeftMenu>> leftMenus) {
//        this.leftMenus = leftMenus;
//    }

    public void setRights(Set<String> rights) {
        this.rights = rights;
    }

    public Set<String> getRights() {
        return rights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getRegInst() {
        return regInst;
    }

    public void setRegInst(String regInst) {
        this.regInst = regInst;
    }

    public String getRegBarnch() {
        return regBarnch;
    }

    public void setRegBarnch(String regBarnch) {
        this.regBarnch = regBarnch;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    @Override
    public String toString() {
        return userName;
    }
}

