package com.demo.dto;

import java.io.Serializable;

public class loginResponse implements Serializable {
    private static final long serialVersionUID = -5818924611322287519L;

    private Boolean success;

    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
