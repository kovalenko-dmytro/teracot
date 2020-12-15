package com.kovalenko.teracot.service.report.impl;

import com.kovalenko.teracot.entity.report.ReportTemplate;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.ReportTemplateRepository;
import com.kovalenko.teracot.service.report.ReportTemplateService;
import com.kovalenko.teracot.service.report.TestResultReportService;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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
    public TestResultReportService findReportServiceByReportName(long testTypeID, String reportName) throws ApplicationException {
        Optional<ReportTemplate> reportTemplate =
            reportTemplateRepository.findByTestType_TestTypeIDAndReportName(testTypeID, reportName);
        try {
            return beanFactory.getBean(reportTemplate.get().getFullQualifiedServiceName(), TestResultReportService.class);
        } catch (Exception e) {
            Object[] params = {reportName, e.getMessage()};
            throw new ApplicationException(messageSource.getMessage("report.service.not.found", params, Locale.ENGLISH));
        }
    }
}
