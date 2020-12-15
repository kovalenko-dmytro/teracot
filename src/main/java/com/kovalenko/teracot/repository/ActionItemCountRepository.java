package com.kovalenko.teracot.repository;

import com.kovalenko.teracot.entity.ai.ActionItemCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionItemCountRepository extends CrudRepository<ActionItemCount, Long> {
}
