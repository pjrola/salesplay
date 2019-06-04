package com.auth.service.entity;

import java.util.Date;

public class TokenState {
    private String token;
    private Date expires;

    public TokenState(String token, Date expires) {
        this.token = token;
        this.expires = expires;
    }

    public String gettoken() {
        return token;
    }

    public void settoken(String token) {
        this.token = token;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
