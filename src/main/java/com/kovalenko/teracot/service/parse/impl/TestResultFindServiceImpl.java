package com.kovalenko.teracot.service.parse.impl;

import com.kovalenko.teracot.entity.report.StatisticReport;
import com.kovalenko.teracot.entity.test.TestType;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.StatisticReportService;
import com.kovalenko.teracot.service.parse.TestResultFindService;
import com.kovalenko.teracot.service.parse.visitor.FileVisitor;
import com.kovalenko.teracot.service.test.TestTypeService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestResultFindServiceImpl implements TestResultFindService {

    private final FileVisitor fileVisitor;
    private final TestTypeService testTypeService;
    private final StatisticReportService statisticReportService;
    private final MessageSource messageSource;

    @Override
    public Map<String, String> findReportsFromResource(long testTypeID, Path pathToResource) throws ApplicationException {
        TestType testType = testTypeService.findById(testTypeID);
        try {
            Files.walkFileTree(pathToResource, fileVisitor);
            Map<String, String> reports = fileVisitor.getReports();
            checkEmptyReportDirectory(pathToResource, testType, reports);
            checkUnexpectedReport(testType, reports);
            return reports;
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    private void checkEmptyReportDirectory(Path pathToResource, TestType testType, Map<String, String> reports) throws ApplicationException {
        if (reports.isEmpty()) {
            Object[] params = {testType.getTestTypeName(), pathToResource};
            throw new ApplicationException(messageSource.getMessage("test.reports.not.found", params, Locale.ENGLISH));
        }
    }

    private void checkUnexpectedReport(TestType testType, Map<String, String> reports) throws ApplicationException {
        List<StatisticReport> reportTemplates = statisticReportService.findByTestTypeID(testType.getTestTypeID());
        for (String reportName : reports.keySet()) {
            boolean absent = reportTemplates.stream()
                .map(StatisticReport::getReportName)
                .noneMatch(report -> report.equalsIgnoreCase(reportName));
            if (absent) {
                throw new ApplicationException(
                    messageSource.getMessage("unexpected.report.found", new Object[]{reportName}, Locale.ENGLISH));
            }
        }
    }
}
