package com.app.entity;

import java.io.Serializable;

public class TPermit implements Serializable {
    private Integer pId;

    private Integer reqUserId;

    private Integer reqImgId;

    private String isPermit;

    private static final long serialVersionUID = 1L;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getReqUserId() {
        return reqUserId;
    }

    public void setReqUserId(Integer reqUserId) {
        this.reqUserId = reqUserId;
    }

    public Integer getReqImgId() {
        return reqImgId;
    }

    public void setReqImgId(Integer reqImgId) {
        this.reqImgId = reqImgId;
    }

    public String getIsPermit() {
        return isPermit;
    }

    public void setIsPermit(String isPermit) {
        this.isPermit = isPermit;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TPermit other = (TPermit) that;
        return (this.getpId() == null ? other.getpId() == null : this.getpId().equals(other.getpId()))
            && (this.getReqUserId() == null ? other.getReqUserId() == null : this.getReqUserId().equals(other.getReqUserId()))
            && (this.getReqImgId() == null ? other.getReqImgId() == null : this.getReqImgId().equals(other.getReqImgId()))
            && (this.getIsPermit() == null ? other.getIsPermit() == null : this.getIsPermit().equals(other.getIsPermit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getpId() == null) ? 0 : getpId().hashCode());
        result = prime * result + ((getReqUserId() == null) ? 0 : getReqUserId().hashCode());
        result = prime * result + ((getReqImgId() == null) ? 0 : getReqImgId().hashCode());
        result = prime * result + ((getIsPermit() == null) ? 0 : getIsPermit().hashCode());
        return result;
    }
}