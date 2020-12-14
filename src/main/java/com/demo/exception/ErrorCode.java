package com.demo.exception;

public enum ErrorCode {

    INTERNAL_SYSTEM_ANOMALY(10000, "系统内部异常"),

    USER_NOT_EXIST(10001, "用户不存在"),

    ACTIVITY_NOT_EXIST(10002, "活动不存在"),

    ACTIVITY_ID_CLOSE(10003, "活动已关闭"),

    ACTIVITY_NOT_START(10004, "活动未开始"),

    ACTIVITY_IS_END(10004, "活动已结束"),

    USER_HAVE_NO_LUCK_DRAW_NUMBER(10005, "用户没有抽奖次数"),
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
