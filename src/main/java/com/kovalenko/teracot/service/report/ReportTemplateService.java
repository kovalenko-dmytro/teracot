package com.kovalenko.teracot.service.report;

import com.kovalenko.teracot.dto.report.ReportTemplateDTO;
import com.kovalenko.teracot.entity.report.ReportTemplate;
import com.kovalenko.teracot.exception.ApplicationException;
import java.util.List;

public interface ReportTemplateService {

    List<ReportTemplate> findAll();
    List<ReportTemplate> findByTestTypeID(long testTypeID);
    TestResultReportService findReportServiceByReportName(long testTypeID, String reportName) throws ApplicationException;
    List<ReportTemplateDTO> getAvailableReports(long testTypeID, long testResultID);
}
