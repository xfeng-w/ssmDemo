package com.demo.dto;

import java.io.Serializable;

public class loginResponse implements Serializable {
    private static final long serialVersionUID = -5818924611322287519L;

    private Long userId;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
