package com.kovalenko.teracot.common;

public enum AppConstants {
    TEST("test");

    AppConstants(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
