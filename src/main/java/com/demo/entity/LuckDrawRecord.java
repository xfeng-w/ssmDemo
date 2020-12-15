package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽奖记录表
 */
public class LuckDrawRecord implements Serializable {
    private static final long serialVersionUID = -7981148342938256029L;

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
     * 奖品Id
     */
    private Long activityPrizeId;

    /**
     * 中奖时间
     */
    private Date luckDrawTime;

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

    /**
     * 奖品类型
     */
    private Integer prizeType;

    /**
     * 奖品价值
     */
    private Double prizeNum;


    public Integer getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(Double prizeNum) {
        this.prizeNum = prizeNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityPrizeId() {
        return activityPrizeId;
    }

    public void setActivityPrizeId(Long activityPrizeId) {
        this.activityPrizeId = activityPrizeId;
    }

    public Date getLuckDrawTime() {
        return luckDrawTime;
    }

    public void setLuckDrawTime(Date luckDrawTime) {
        this.luckDrawTime = luckDrawTime;
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
