package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.Performance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findBySalonId(Long salonId);

    Optional<List<Performance>> findByStatus(GlobalStatus status);

    List<Performance> findByPerformanceUndercategoryId(Long performanceUndercategoryId);
}
