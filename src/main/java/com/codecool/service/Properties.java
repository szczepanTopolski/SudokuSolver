package com.codecool.service;

public enum Properties {
    MAX_WIDTH(9), MAX_HEIGHT(9);

    private final int value;

    Properties(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
