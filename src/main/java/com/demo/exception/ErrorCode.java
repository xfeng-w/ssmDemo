package com.demo.exception;

public enum ErrorCode {

    INTERNAL_SYSTEM_ANOMALY(10000, "系统内部异常"),

    USER_NOT_EXIST(10001, "用户不存在"),

    ACTIVITY_NOT_EXIST(10002, "活动不存在"),

    ACTIVITY_ID_CLOSE(10003, "活动已关闭"),

    ACTIVITY_NOT_START(10004, "活动未开始"),

    ACTIVITY_IS_END(10004, "活动已结束"),

    USER_HAVE_NO_LUCK_DRAW_NUMBER(10005, "用户没有抽奖次数"),

    USER_PASSWORD_ERROR(10006, "用户密码错误"),

    REQUIRED_FIELDS_NOT_BE_EMPTY(10007, "必填项不能为空"),

    ACCOUNT_OR_PHONE_EXIST(10008, "账号或手机号已被注册"),

    USER_LOGON_FAILURE(10009, "用户登录失效"),
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
