package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.time.TimeInfoType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeInfoTypeRepository extends CrudRepository<TimeInfoType, Long> {
}
