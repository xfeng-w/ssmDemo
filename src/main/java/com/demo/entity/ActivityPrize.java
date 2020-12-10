package com.demo.entity;

import java.io.Serializable;

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
     * 奖品Id
     */
    private Long prizeId;

    /**
     * 中奖率
     */
    private Double probability;

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
}
