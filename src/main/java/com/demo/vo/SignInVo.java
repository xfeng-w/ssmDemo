package com.demo.vo;

import java.io.Serializable;

public class SignInVo implements Serializable {
    private static final long serialVersionUID = -5688278850185823781L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 签到天数
     */
    private Integer signInDays;

    /**
     * 连续签到天数
     */
    private Integer continuousSignInDays;

    /**
     * 签到日期，逗号分隔
     */
    private String signInDates;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getSignInDays() {
        return signInDays;
    }

    public void setSignInDays(Integer signInDays) {
        this.signInDays = signInDays;
    }

    public Integer getContinuousSignInDays() {
        return continuousSignInDays;
    }

    public void setContinuousSignInDays(Integer continuousSignInDays) {
        this.continuousSignInDays = continuousSignInDays;
    }

    public String getSignInDates() {
        return signInDates;
    }

    public void setSignInDates(String signInDates) {
        this.signInDates = signInDates;
    }
}
