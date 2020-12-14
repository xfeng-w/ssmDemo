package com.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户剩余抽奖次数奖
 */
public class UserLuckDrawNumber implements Serializable {

    private static final long serialVersionUID = 1036573774660308188L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 抽奖次数
     */
    private Integer luckDrawNumber;

    /**
     * 抽奖日期
     */
    private Date luckDrawDate;

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

    public Integer getLuckDrawNumber() {
        return luckDrawNumber;
    }

    public void setLuckDrawNumber(Integer luckDrawNumber) {
        this.luckDrawNumber = luckDrawNumber;
    }

    public Date getLuckDrawDate() {
        return luckDrawDate;
    }

    public void setLuckDrawDate(Date luckDrawDate) {
        this.luckDrawDate = luckDrawDate;
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
