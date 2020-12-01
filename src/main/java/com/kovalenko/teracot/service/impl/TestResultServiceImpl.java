package com.kovalenko.teracot.service.impl;

import com.kovalenko.teracot.dto.collected.CollectedTestResultDTO;
import com.kovalenko.teracot.service.TestResultService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    @Override
    public List<CollectedTestResultDTO> getSummaryApplyingStatistics(String testTypeID) {
        return null;
    }

    @Override
    public void uploadTestResults(String testType, String pathToResource) {

    }
}
