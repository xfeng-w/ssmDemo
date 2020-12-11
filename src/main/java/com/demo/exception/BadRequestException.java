package com.demo.exception;

public class BadRequestException extends Exception {
    private static final long serialVersionUID = -5040102560780177984L;

    private Integer code;

    private String msg;

    public BadRequestException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
