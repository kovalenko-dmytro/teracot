package com.kovalenko.teracot.service;

import com.kovalenko.teracot.exception.ApplicationException;
import java.nio.file.Path;

public interface TestResultValidateService {

    void validate(long testTypeID, Path pathToSource) throws ApplicationException;
}
