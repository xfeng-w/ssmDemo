package com.demo.enums;

public enum Status {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用"),
    ;

    private Integer value;

    private String name;

    Status(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
