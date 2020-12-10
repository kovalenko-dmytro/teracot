package com.kovalenko.teracot.service.test.impl;

import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.TestResultRepository;
import com.kovalenko.teracot.service.parse.TestResultFindService;
import com.kovalenko.teracot.service.test.TestResultService;
import com.kovalenko.teracot.service.test.TestResultValidateService;
import com.kovalenko.teracot.service.test.TestTypeService;
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

    private final TestResultRepository testResultRepository;
    private final TestTypeService testTypeService;
    private final TestResultValidateService testResultValidateService;
    private final TestResultFindService testResultFindService;
    private final MessageSource messageSource;

    @Override
    public List<TestResult> getTestResults(long testTypeID) {
        return testResultRepository.findByTestType_testTypeID(testTypeID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadTestResults(long testTypeID, String pathToResource) throws ApplicationException {
        Path path = Paths.get(pathToResource);
        testResultValidateService.validate(testTypeID, path);
        Map<String, String> reports = testResultFindService.findReportsFromResource(testTypeID, path);
    }
}
