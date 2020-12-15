package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.collected.CollectedStatistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectedStatisticRepository extends CrudRepository<CollectedStatistic, Long> {
}
