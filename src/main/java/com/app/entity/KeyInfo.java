package com.app.entity;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-05-11 13:15
 * Created with IDEA
 */
public class KeyInfo {

    private Boolean isSuccess;

    private String publicKey;

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
