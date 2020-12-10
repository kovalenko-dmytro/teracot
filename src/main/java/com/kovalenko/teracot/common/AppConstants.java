package com.kovalenko.teracot.common;

public enum AppConstants {

    SEARCH_TEST_DIRECTORY("statistic"),
    CSV_EXTENSION(".csv"),
    SKIP_JAR("before_jar"),
    SKIP_LOG_ZIP("log.zip");

    AppConstants(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
