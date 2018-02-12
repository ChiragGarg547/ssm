package com.app.entity;

import java.io.Serializable;

public class TParam implements Serializable {
    private Integer paramId;

    private Integer userId;

    private String pubKey;

    private String priKey;

    private static final long serialVersionUID = 1L;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
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
        TParam other = (TParam) that;
        return (this.getParamId() == null ? other.getParamId() == null : this.getParamId().equals(other.getParamId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPubKey() == null ? other.getPubKey() == null : this.getPubKey().equals(other.getPubKey()))
            && (this.getPriKey() == null ? other.getPriKey() == null : this.getPriKey().equals(other.getPriKey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getParamId() == null) ? 0 : getParamId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPubKey() == null) ? 0 : getPubKey().hashCode());
        result = prime * result + ((getPriKey() == null) ? 0 : getPriKey().hashCode());
        return result;
    }
}