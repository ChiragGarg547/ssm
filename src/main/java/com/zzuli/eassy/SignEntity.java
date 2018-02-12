package com.zzuli.eassy;

import java.io.Serializable;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-1-25 14:21
 * Created with IDEA
 */
public class SignEntity implements Serializable{

    protected static final long serialVersionUID = 0;

    private String filename;

    private String publicKey;

    private String privateKey;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
