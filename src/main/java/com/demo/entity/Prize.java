package com.demo.entity;

import com.demo.enums.PrizeType;

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
    private PrizeType prizeType;

    /**
     * 奖品价值
     */
    private Double prizeNum;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public PrizeType getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(PrizeType prizeType) {
        this.prizeType = prizeType;
    }

    public Double getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(Double prizeNum) {
        this.prizeNum = prizeNum;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
