package com.kovalenko.teracot.service.impl;

import com.kovalenko.teracot.dto.collected.CollectedTestResultDTO;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.service.TestResultService;
import com.kovalenko.teracot.service.TestResultValidateService;
import com.kovalenko.teracot.service.TestTypeService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestTypeService testTypeService;
    private final TestResultValidateService testResultValidateService;
    private final MessageSource messageSource;

    @Override
    public List<CollectedTestResultDTO> getSummaryApplyingStatistics(String testTypeID) {
        return null;
    }

    @Override
    public void uploadTestResults(String testType, String pathToResource) throws ApplicationException {
        Path path = Paths.get(pathToResource);
        testResultValidateService.validate(testType, path);

        try {
            List<Path> collect = Files.walk(Paths.get(pathToResource), 10)
                .filter(path1 -> path1.getFileName().toString().endsWith(".zip"))
                .collect(Collectors.toList());
            for (Path path2: collect) {
                System.out.println(path2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
