package com.kovalenko.teracot.service.impl;

import com.kovalenko.teracot.entity.test.TestType;
import com.kovalenko.teracot.repository.TestTypeRepository;
import com.kovalenko.teracot.service.TestTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestTypeServiceImpl implements TestTypeService {

    private final TestTypeRepository testTypeRepository;

    @Override
    public List<TestType> findAll() {
        return (List<TestType>) testTypeRepository.findAll();
    }
}
