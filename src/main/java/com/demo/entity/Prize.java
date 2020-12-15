package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖品表
 */
public class Prize implements Serializable {
    private static final long serialVersionUID = 7493173661516875987L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 奖品名称
     */
    private String name;

    /**
     * 奖品类型
     */
    private Integer prizeType;

    /**
     * 用户获取最大数量限制
     */
    private Integer userMaxNum;

    /**
     * 最大数量限制
     */
    private Integer maxNum;

    /**
     * 每天最大获取数量
     */
    private Integer dayMaxNum;

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

    public Integer getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    public Integer getUserMaxNum() {
        return userMaxNum;
    }

    public void setUserMaxNum(Integer userMaxNum) {
        this.userMaxNum = userMaxNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getDayMaxNum() {
        return dayMaxNum;
    }

    public void setDayMaxNum(Integer dayMaxNum) {
        this.dayMaxNum = dayMaxNum;
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
