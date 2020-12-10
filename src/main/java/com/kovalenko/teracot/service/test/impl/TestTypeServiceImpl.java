package com.kovalenko.teracot.service.test.impl;

import com.kovalenko.teracot.entity.test.TestType;
import com.kovalenko.teracot.exception.ApplicationException;
import com.kovalenko.teracot.repository.TestTypeRepository;
import com.kovalenko.teracot.service.test.TestTypeService;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestTypeServiceImpl implements TestTypeService {

    private final TestTypeRepository testTypeRepository;
    private final MessageSource messageSource;

    @Override
    public List<TestType> findAll() {
        return (List<TestType>) testTypeRepository.findAll();
    }

    @Override
    public TestType findById(long testTypeID) throws ApplicationException{
        return testTypeRepository
            .findById(testTypeID)
            .orElseThrow(() -> new ApplicationException(
                messageSource.getMessage("test.type.not.exist", new Object[] {testTypeID}, Locale.ENGLISH)
            ));
    }
}
