package com.xf.oschina.module.login.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Token {
    @Id
    private String uid;//":12
    private String access_token;//":"8447ff97-9b8c-4224-9cec-63b97d34ba65",
    private String refresh_token;//":"8447ff97-9b8c-4224-9cec",
    private String token_type;//":"bearer",
    private String expires_in;//":43199,

    @Generated(hash = 1770629917)
    public Token(String uid, String access_token, String refresh_token,
            String token_type, String expires_in) {
        this.uid = uid;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    @Generated(hash = 79808889)
    public Token() {
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
