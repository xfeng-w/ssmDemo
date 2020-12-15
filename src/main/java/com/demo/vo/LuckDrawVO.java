package com.demo.vo;

import com.demo.entity.ActivityPrize;
import com.demo.entity.Prize;

import java.io.Serializable;

public class LuckDrawVO implements Serializable {
    private static final long serialVersionUID = 3164569342720789892L;

    /**
     * 是否中奖
     */
    private Boolean winning;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品数量
     */
    private Double prizeNum;

    /**
     * 奖品Id
     */
    private Long prizeId;

    public Boolean getWinning() {
        return winning;
    }

    public void setWinning(Boolean winning) {
        this.winning = winning;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
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
}
