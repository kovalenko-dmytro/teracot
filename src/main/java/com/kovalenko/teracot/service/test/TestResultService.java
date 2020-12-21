package com.kovalenko.teracot.service.test;

import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.exception.ApplicationException;
import java.util.List;

public interface TestResultService {

    List<TestResult> getTestResults(long testTypeID);
    void uploadTestResults(long testTypeID, String pathToResource) throws ApplicationException;
    void apply(long testResultID, String devBuildNumber, String testBuildNumber) throws ApplicationException;
    void deleteByTestResultID(long testResultID);
}
