package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.RatingPerformance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingPerformanceRepository extends JpaRepository<RatingPerformance, Long> {
    List<RatingPerformance> findByUserId(Long userId);

    List<RatingPerformance> findByPerformanceId(Long performanceId);
}
