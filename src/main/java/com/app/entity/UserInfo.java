package com.app.entity;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-9 9:54
 * Created with IDEA
 */
public class UserInfo {

    private Boolean isSuccess;

    private String publicKey;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
