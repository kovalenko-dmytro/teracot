package com.kovalenko.teracot.service.test.impl;

import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.TestResultRepository;
import com.kovalenko.teracot.service.find.TestResultFindService;
import com.kovalenko.teracot.service.report.ReportTemplateService;
import com.kovalenko.teracot.service.test.TestResultService;
import com.kovalenko.teracot.service.test.TestResultValidateService;
import com.kovalenko.teracot.service.test.TestTypeService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestResultRepository testResultRepository;
    private final TestTypeService testTypeService;
    private final TestResultValidateService testResultValidateService;
    private final TestResultFindService testResultFindService;
    private final ReportTemplateService reportTemplateService;
    private final MessageSource messageSource;

    @Override
    public List<TestResult> getTestResults(long testTypeID) {
        return testResultRepository.findByTestType_testTypeIDAndIsAppliedOrderByCreatedDesc(testTypeID, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadTestResults(long testTypeID, String pathToResource) throws ApplicationException {
        Path path = Paths.get(pathToResource);
        testResultValidateService.validate(testTypeID, path);
        TestResult testResult = saveTestResultInfo(testTypeID, pathToResource);
        Map<String, String> reports = testResultFindService.findReportsFromResource(testTypeID, path);
        for (Map.Entry<String, String> entry : reports.entrySet()) {
            reportTemplateService
                .findReportServiceByReportName(testTypeID, entry.getKey())
                .saveReportContent(testResult, entry.getValue());
        }
    }

    @Override
    public void apply(long testResultID, String devBuildNumber, String testBuildNumber) throws ApplicationException {
        TestResult testResult = findByID(testResultID);
        testResult.setApplied(true);
        testResult.setDevBuildNumber(devBuildNumber);
        testResult.setTestBuildNumber(testBuildNumber);
        testResult.setAppliedTime(LocalDateTime.now());
        testResultRepository.save(testResult);
    }

    @Override
    public void deleteByTestResultID(long testResultID) {
        testResultRepository.deleteById(testResultID);
    }

    private TestResult findByID(long testResultID) throws ApplicationException {
        return testResultRepository.findById(testResultID)
            .orElseThrow(() -> new ApplicationException(
                messageSource.getMessage("test.result.not.exist", new Object[]{testResultID}, Locale.ENGLISH)));
    }

    private TestResult saveTestResultInfo(long testTypeID, String pathToResource) throws ApplicationException {
        String[] pathArray = pathToResource.split("\\\\");
        TestResult testResult = TestResult.builder()
            .userName(pathArray[pathArray.length - 3])
            .taskName(pathArray[pathArray.length - 2])
            .dialectPair(pathArray[pathArray.length - 1])
            .created(LocalDateTime.now())
            .testType(testTypeService.findById(testTypeID))
            .build();
        return testResultRepository.save(testResult);
    }
}
