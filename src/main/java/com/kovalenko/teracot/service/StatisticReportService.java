package com.kovalenko.teracot.service;

import com.kovalenko.teracot.entity.report.StatisticReport;
import java.util.List;

public interface StatisticReportService {

    List<StatisticReport> findAll();
    List<StatisticReport> findByTestTypeID(long testTypeID);
}
