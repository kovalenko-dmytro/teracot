package com.kovalenko.teracot.service.impl;

import com.kovalenko.teracot.dto.collected.CollectedTestResultDTO;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.TestResultService;
import com.kovalenko.teracot.service.TestResultValidateService;
import com.kovalenko.teracot.service.TestTypeService;
import com.kovalenko.teracot.service.parse.TestResultFindService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestTypeService testTypeService;
    private final TestResultValidateService testResultValidateService;
    private final TestResultFindService testResultFindService;
    private final MessageSource messageSource;

    @Override
    public List<CollectedTestResultDTO> getSummaryApplyingStatistics(long testTypeID) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadTestResults(long testTypeID, String pathToResource) throws ApplicationException {
        Path path = Paths.get(pathToResource);
        testResultValidateService.validate(testTypeID, path);
        Map<String, String> reports = testResultFindService.findReportsFromResource(testTypeID, path);
    }
}
