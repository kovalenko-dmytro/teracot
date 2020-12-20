package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.collected.CollectedStatistic;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectedStatisticRepository extends CrudRepository<CollectedStatistic, Long> {
    Optional<CollectedStatistic> findByTestResult_testResultID(long testResultID);
}
