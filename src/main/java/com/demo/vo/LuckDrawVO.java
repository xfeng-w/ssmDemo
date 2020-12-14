package com.demo.vo;

import com.demo.entity.Prize;

import java.io.Serializable;

public class LuckDrawVO implements Serializable {
    private static final long serialVersionUID = 3164569342720789892L;

    /**
     * 是否中奖
     */
    private Boolean winning;

    /**
     * 中奖奖品
     */
    private Prize prize;

    public Boolean getWinning() {
        return winning;
    }

    public void setWinning(Boolean winning) {
        this.winning = winning;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
