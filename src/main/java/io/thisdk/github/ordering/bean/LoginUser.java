package io.thisdk.github.ordering.bean;

import io.thisdk.github.ordering.bean.CmsUser;


public class LoginUser {

    private String token;

    private Long loginTime;

    private Long expireTime;

    private CmsUser user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUser() {
    }

    public LoginUser(CmsUser user) {
        this.user = user;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public CmsUser getUser() {
        return user;
    }

    public void setUser(CmsUser user) {
        this.user = user;
    }
}
