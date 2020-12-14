package com.demo.enums;

/**
 * 奖品类型
 */
public enum PrizeType {
    RED_PACKET(1, "红包"),

    WING(2, "元宝"),

    GOLD_COINS(3, "金币"),
    ;

    private Integer value;

    private String name;

    PrizeType(Integer value, String name) {
        this.value = value;

        this.name = name;
    }
}
