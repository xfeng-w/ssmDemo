package com.demo.vo;

import java.io.Serializable;
import java.util.Date;

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
     * 签到日期，逗号分隔
     */
    private Date lastSignInDates;

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

    public Date getLastSignInDates() {
        return lastSignInDates;
    }

    public void setLastSignInDates(Date lastSignInDates) {
        this.lastSignInDates = lastSignInDates;
    }
}
