package com.app.entity;

public class PermitInfo {

    private String userName;

    private Integer pId;

    private String imgType;

    private String remark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermitInfo that = (PermitInfo) o;

        if (!userName.equals(that.userName)) return false;
        if (!pId.equals(that.pId)) return false;
        if (imgType != null ? !imgType.equals(that.imgType) : that.imgType != null) return false;
        return remark != null ? remark.equals(that.remark) : that.remark == null;
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + pId.hashCode();
        result = 31 * result + (imgType != null ? imgType.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
