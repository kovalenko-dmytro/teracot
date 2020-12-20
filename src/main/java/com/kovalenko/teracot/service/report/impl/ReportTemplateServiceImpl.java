package com.kovalenko.teracot.service.report.impl;

import com.kovalenko.teracot.dto.report.ReportInfoDTO;
import com.kovalenko.teracot.dto.report.ReportTemplateDTO;
import com.kovalenko.teracot.entity.report.ReportTemplate;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.ReportTemplateRepository;
import com.kovalenko.teracot.service.report.ReportTemplateService;
import com.kovalenko.teracot.service.report.TestResultReportService;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository reportTemplateRepository;
    private final BeanFactory beanFactory;
    private final MessageSource messageSource;

    @Override
    public List<ReportTemplate> findAll() {
        return (List<ReportTemplate>) reportTemplateRepository.findAll();
    }

    @Override
    public List<ReportTemplate> findByTestTypeID(long testTypeID) {
        return reportTemplateRepository.findByTestType_TestTypeID(testTypeID);
    }

    @Override
    public ReportTemplate findByReportID(long reportID) throws ApplicationException {
        return reportTemplateRepository.findById(reportID)
            .orElseThrow(() ->
                new ApplicationException(
                    messageSource.getMessage("report.not.exist", new Object[] {reportID}, Locale.ENGLISH)));
    }

    @Override
    public TestResultReportService findReportServiceByReportName(long testTypeID, String reportName) throws ApplicationException {
        Optional<ReportTemplate> reportTemplate =
            reportTemplateRepository.findByTestType_TestTypeIDAndReportName(testTypeID, reportName);
        try {
            return beanFactory.getBean(reportTemplate.get().getServiceName(), TestResultReportService.class);
        } catch (Exception e) {
            Object[] params = {reportName, e.getMessage()};
            throw new ApplicationException(messageSource.getMessage("report.service.not.found", params, Locale.ENGLISH));
        }
    }

    @Override
    public List<ReportTemplateDTO> getAvailableReports(long testTypeID, long testResultID) {
        return findByTestTypeID(testTypeID).stream()
            .map(reportTemplate ->
                ReportTemplateDTO.builder()
                    .reportID(reportTemplate.getReportID())
                    .reportName(reportTemplate.getReportName())
                    .testResultID(testResultID)
                    .build())
            .collect(Collectors.toList());
    }

    @Override
    public ReportInfoDTO getReportInfo(long testTypeID, long testResultID, long reportID) throws ApplicationException {
        ReportTemplate reportTemplate = findByReportID(reportID);
        return findReportServiceByReportName(testTypeID, reportTemplate.getReportName())
            .getReportInfo(reportTemplate, testResultID, testTypeID);
    }
}
