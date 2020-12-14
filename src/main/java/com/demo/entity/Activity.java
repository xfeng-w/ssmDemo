package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动表
 */
public class Activity implements Serializable {
    private static final long serialVersionUID = -3637494662315225210L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 高概率
     */
    private Double highProbability;

    /**
     * 中概率
     */
    private Double mediumProbability;

    /**
     * 低概率
     */
    private Double lowProbability;

    /**
     * 启用状态
     */
    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getHighProbability() {
        return highProbability;
    }

    public void setHighProbability(Double highProbability) {
        this.highProbability = highProbability;
    }

    public Double getMediumProbability() {
        return mediumProbability;
    }

    public void setMediumProbability(Double mediumProbability) {
        this.mediumProbability = mediumProbability;
    }

    public Double getLowProbability() {
        return lowProbability;
    }

    public void setLowProbability(Double lowProbability) {
        this.lowProbability = lowProbability;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
