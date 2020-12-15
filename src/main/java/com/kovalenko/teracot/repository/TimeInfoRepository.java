package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.time.TimeInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeInfoRepository extends CrudRepository<TimeInfo, Long> {
}
