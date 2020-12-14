package com.demo.exception;

public enum ErrorCode {

    INTERNAL_SYSTEM_ANOMALY(10000, "系统内部异常"),

    USER_NOT_EXIST(10001, "用户不存在"),

    ACTIVITY_NOT_EXIST(10002, "活动不存在"),
    ;

    private Integer code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCode getErrorCodeByCode(Integer code) {
        ErrorCode[] errorCodes = values();
        for (ErrorCode item : errorCodes) {
            if (code.equals(item.code)) {
                return item;
            }
        }
        return null;
    }
}
