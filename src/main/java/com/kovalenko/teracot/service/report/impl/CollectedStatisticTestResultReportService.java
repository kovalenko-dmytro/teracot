package com.kovalenko.teracot.service.report.impl;

import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.ActionItemCountRepository;
import com.kovalenko.teracot.repository.CollectedStatisticRepository;
import com.kovalenko.teracot.repository.TimeInfoRepository;
import com.kovalenko.teracot.repository.TimeInfoTypeRepository;
import com.kovalenko.teracot.service.report.TestResultReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("collectedStatisticTestResultReportService")
@RequiredArgsConstructor
public class CollectedStatisticTestResultReportService implements TestResultReportService {

    private final CollectedStatisticRepository collectedStatisticRepository;
    private final TimeInfoRepository timeInfoRepository;
    private final TimeInfoTypeRepository timeInfoTypeRepository;
    private final ActionItemCountRepository actionItemCountRepository;
    private final MessageSource messageSource;

    @Override
    public void saveReportContent(TestResult testResult, String reportContent) throws ApplicationException {

    }
}
