package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动奖品表
 */
public class ActivityPrize implements Serializable {
    private static final long serialVersionUID = -3579377371548606447L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 奖品名称
     */
    private String name;

    /**
     * 奖品数量
     */
    private Double prizeNum;

    /**
     * 奖品Id
     */
    private Long prizeId;

    /**
     * 中奖率%
     */
    private Double probability;

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
     * 奖品详情
     */
    private Prize prize;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(Double prizeNum) {
        this.prizeNum = prizeNum;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
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

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
