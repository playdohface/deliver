package com.karlerikenkelmann.deliver.repository;

import com.karlerikenkelmann.deliver.entity.TransitLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransitLogRepository extends JpaRepository<TransitLog, Integer> {
}
