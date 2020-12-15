package com.kovalenko.teracot.service.report;

import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;

public interface TestResultReportService {

    void saveReportContent(TestResult testResult, String reportContent) throws ApplicationException;
}
