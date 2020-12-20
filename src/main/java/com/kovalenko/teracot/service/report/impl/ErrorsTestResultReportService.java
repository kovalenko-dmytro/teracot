package com.kovalenko.teracot.service.report.impl;

import com.kovalenko.teracot.dto.report.ReportInfoDTO;
import com.kovalenko.teracot.entity.report.ReportTemplate;
import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.report.TestResultReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("errorsTestResultReportService")
@RequiredArgsConstructor
public class ErrorsTestResultReportService implements TestResultReportService {

    @Override
    public void saveReportContent(TestResult testResult, String reportContent) throws ApplicationException {

    }

    @Override
    public ReportInfoDTO getReportInfo(ReportTemplate reportID, long testResultID, long testTypeID) throws ApplicationException {
        return null;
    }
}
