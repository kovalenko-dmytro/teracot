package com.kovalenko.teracot.service;

import com.kovalenko.teracot.dto.collected.CollectedTestResultDTO;
import java.util.List;

public interface TestResultService {

    List<CollectedTestResultDTO> getSummaryApplyingStatistics(String testTypeID);
    void uploadTestResults(String testType, String pathToResource);
}
