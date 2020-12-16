package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户签到记录表
 */
public class UserSignInRecord implements Serializable {
    private static final long serialVersionUID = 7621985788708062276L;

    /**
     * 主键id
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 版本号
     */
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
