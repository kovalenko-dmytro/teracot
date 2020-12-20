package com.kovalenko.teracot.service.report.impl;

import com.kovalenko.teracot.dto.collected.CollectedStatisticFieldNameDTO;
import com.kovalenko.teracot.dto.collected.CollectedStatisticReportInfoDTO;
import com.kovalenko.teracot.dto.report.ReportInfoDTO;
import com.kovalenko.teracot.entity.collected.CollectedStatistic;
import com.kovalenko.teracot.entity.report.ReportTemplate;
import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.CollectedStatisticRepository;
import com.kovalenko.teracot.repository.TestResultRepository;
import com.kovalenko.teracot.service.parser.TestResultReportParser;
import com.kovalenko.teracot.service.report.TestResultReportService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("collectedStatisticTestResultReportService")
@RequiredArgsConstructor
public class CollectedStatisticTestResultReportService implements TestResultReportService {

    private final TestResultReportParser<CollectedStatistic, CollectedStatisticFieldNameDTO> collectedStatisticTestResultReportParser;
    private final CollectedStatisticRepository collectedStatisticRepository;
    private final TestResultRepository testResultRepository;
    private final MessageSource messageSource;

    @Override
    public void saveReportContent(TestResult testResult, String reportContent) throws ApplicationException {
        CollectedStatistic result = collectedStatisticTestResultReportParser.parse(reportContent);
        result.setTestResult(testResult);
        collectedStatisticRepository.save(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReportInfoDTO getReportInfo(ReportTemplate reportTemplate, long testResultID, long testTypeID) throws ApplicationException {
        CollectedStatistic current = getCollectedStatisticReportByTestResultID(testResultID);

        long lastAppliedTestResultID = getLastAppliedTestResultID(testTypeID, current);
        CollectedStatistic lastApplied = getCollectedStatisticReportByTestResultID(lastAppliedTestResultID);

        CollectedStatisticReportInfoDTO csReportInfoDTO = CollectedStatisticReportInfoDTO.builder()
            .reportName(reportTemplate.getReportName())
            .fieldNames(collectedStatisticTestResultReportParser.getReportFieldNames())
            .current(current.convertToDTO())
            .lastApplied(Objects.nonNull(lastApplied) ? lastApplied.convertToDTO() : null)
            .build();
        return ReportInfoDTO.builder()
            .reportID(reportTemplate.getReportID())
            .collectedStatisticReportInfoDTO(csReportInfoDTO)
            .build();
    }

    private Long getLastAppliedTestResultID(long testTypeID, CollectedStatistic current) {
        return testResultRepository
            .findFirstByIsAppliedAndDialectPairAndTestType_TestTypeIDOrderByCreatedDesc(
                true,
                current.getTestResult().getDialectPair(),
                testTypeID)
            .map(TestResult::getTestResultID)
            .orElse( -1L);
    }

    private CollectedStatistic getCollectedStatisticReportByTestResultID(long testResultID) {
        return collectedStatisticRepository.findByTestResult_testResultID(testResultID)
            .orElse(null);
    }

}
