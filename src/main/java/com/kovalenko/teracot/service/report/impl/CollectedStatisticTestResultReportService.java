package com.kovalenko.teracot.service.report.impl;

import com.kovalenko.teracot.entity.collected.CollectedStatistic;
import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.CollectedStatisticRepository;
import com.kovalenko.teracot.service.parser.TestResultReportParser;
import com.kovalenko.teracot.service.report.TestResultReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("collectedStatisticTestResultReportService")
@RequiredArgsConstructor
public class CollectedStatisticTestResultReportService implements TestResultReportService {

    private final TestResultReportParser<CollectedStatistic> collectedStatisticTestResultReportParser;
    private final CollectedStatisticRepository collectedStatisticRepository;
    private final MessageSource messageSource;

    @Override
    public void saveReportContent(TestResult testResult, String reportContent) throws ApplicationException {
        CollectedStatistic result = collectedStatisticTestResultReportParser.parse(reportContent);
        result.setTestResult(testResult);
        result.getTimeInfo().forEach(timeInfo -> timeInfo.setCollectedStatistic(result));
        result.getActionItemCounts().forEach(actionItemCount -> actionItemCount.setCollectedStatistic(result));
        collectedStatisticRepository.save(result);
    }
}
