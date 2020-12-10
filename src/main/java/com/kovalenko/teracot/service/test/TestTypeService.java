package com.kovalenko.teracot.service.test;

import com.kovalenko.teracot.entity.test.TestType;
import com.kovalenko.teracot.exception.ApplicationException;
import java.util.List;

public interface TestTypeService {

    List<TestType> findAll();
    TestType findById(long testTypeID) throws ApplicationException;
}
