package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.Performance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findBySalonId(Long salonId);

    List<Performance> findByPerformanceUndercategoryId(Long performanceUndercategoryId);
}
