package com.kovalenko.teracot.service;

import com.kovalenko.teracot.exception.ApplicationException;
import java.nio.file.Path;

public interface TestResultValidateService {

    void validate(String testType, Path pathToSource) throws ApplicationException;
}
