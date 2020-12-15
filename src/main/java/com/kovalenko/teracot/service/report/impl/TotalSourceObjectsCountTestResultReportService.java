package com.kovalenko.teracot.service.report.impl;

import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.report.TestResultReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("com.kovalenko.teracot.service.report.impl.TotalSourceObjectsCountTestResultReportService")
@RequiredArgsConstructor
public class TotalSourceObjectsCountTestResultReportService implements TestResultReportService {

    @Override
    public void saveReportContent(TestResult testResult, String reportContent) throws ApplicationException {

    }
}
