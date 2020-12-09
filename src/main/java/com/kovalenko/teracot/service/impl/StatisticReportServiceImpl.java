package com.kovalenko.teracot.service.impl;

import com.kovalenko.teracot.entity.report.StatisticReport;
import com.kovalenko.teracot.repository.StatisticReportRepository;
import com.kovalenko.teracot.service.StatisticReportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticReportServiceImpl implements StatisticReportService {

    private final StatisticReportRepository statisticReportRepository;

    @Override
    public List<StatisticReport> findAll() {
        return (List<StatisticReport>) statisticReportRepository.findAll();
    }

    @Override
    public List<StatisticReport> findByTestTypeID(long testTypeID) {
        return statisticReportRepository.findByTestType_TestTypeID(testTypeID);
    }
}
