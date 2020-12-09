package com.kovalenko.teracot.common;

public enum AppConstants {

    SEARCH_TEST_DIRECTORY("statistic"),
    CSV_EXTENSION(".csv");

    AppConstants(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
