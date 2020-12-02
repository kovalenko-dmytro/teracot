package com.kovalenko.teracot.service.impl;

import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.TestResultValidateService;
import com.kovalenko.teracot.service.TestTypeService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestResultValidateServiceImpl implements TestResultValidateService {

    private final TestTypeService testTypeService;
    private final MessageSource messageSource;

    @Override
    public void validate(String testType, Path pathToSource) throws ApplicationException {
        validateTestType(testType);
        validatePathToResource(pathToSource);
    }

    private void validateTestType(String testType) throws ApplicationException {
        testTypeService.findAll().stream()
            .filter(type -> type.getTestTypeName().equalsIgnoreCase(testType))
            .findFirst()
            .orElseThrow(() ->
                new ApplicationException(messageSource.getMessage(
                    "test.type.not.exist", new Object[] {testType}, Locale.ENGLISH)));
    }

    private void validatePathToResource(Path pathToSource) throws ApplicationException {
        if (Files.notExists(pathToSource) || directoryIsEmpty(pathToSource)) {
            throw new ApplicationException(messageSource.getMessage(
                "test.type.not.exist", new Object[] {pathToSource}, Locale.ENGLISH));
        }
    }

    private boolean directoryIsEmpty(Path path) {
        try {
            return Files.walk(path).count() == 0;
        } catch (IOException e) {
            return true;
        }
    }
}
