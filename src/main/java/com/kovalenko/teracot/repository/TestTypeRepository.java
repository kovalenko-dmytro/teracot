package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.test.TestType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTypeRepository extends CrudRepository<TestType, Long> {
}
