package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.report.StatisticReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticReportRepository extends CrudRepository<StatisticReport, Long> {
}
