package com.kovalenko.teracot.service.parser;

import com.kovalenko.teracot.exception.ApplicationException;

public interface TestResultReportParser<T> {

    T parse(String reportContent) throws ApplicationException;
}
