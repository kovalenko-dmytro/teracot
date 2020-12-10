package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.test.TestResult;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends CrudRepository<TestResult, Long> {

    List<TestResult> findByTestType_testTypeID(long testTypeID);
}
