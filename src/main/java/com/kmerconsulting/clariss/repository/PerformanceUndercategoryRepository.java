package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.PerformanceUndercategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceUndercategoryRepository extends JpaRepository<PerformanceUndercategory, Long> {
    List<PerformanceUndercategory> findByName(String name);

    List<PerformanceUndercategory> findByPerformanceCategoryId(Long performanceCategoryId);
}
