package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.report.ReportTemplate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTemplateRepository extends CrudRepository<ReportTemplate, Long> {

    List<ReportTemplate> findByTestType_TestTypeID(long testTypeID);
    Optional<ReportTemplate> findByTestType_TestTypeIDAndReportName(long testTypeID, String reportName);
}
