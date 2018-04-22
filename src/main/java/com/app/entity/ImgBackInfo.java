package com.app.entity;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-9 9:54
 * Created with IDEA
 */
public class ImgBackInfo {

    public ImgBackInfo(){
        this.isSuccess = false;
        this.backInfo = "默认错误";
    }

    private Boolean isSuccess;

    private String backInfo;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getBackInfo() {
        return backInfo;
    }

    public void setBackInfo(String backInfo) {
        this.backInfo = backInfo;
    }
}
