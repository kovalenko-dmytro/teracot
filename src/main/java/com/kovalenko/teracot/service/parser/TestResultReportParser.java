package com.kovalenko.teracot.service.parser;

import com.kovalenko.teracot.exception.ApplicationException;

public interface TestResultReportParser<T, D> {

    T parse(String reportContent) throws ApplicationException;
    D getReportFieldNames();
}
