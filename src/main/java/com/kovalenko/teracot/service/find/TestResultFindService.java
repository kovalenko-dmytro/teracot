package com.kovalenko.teracot.service.find;

import com.kovalenko.teracot.exception.ApplicationException;
import java.nio.file.Path;
import java.util.Map;

public interface TestResultFindService {

    Map<String, String> findReportsFromResource(long testTypeID, Path pathToResource) throws ApplicationException;
}
