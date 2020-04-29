package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.PerformanceCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceCategoryRepository extends JpaRepository<PerformanceCategory, Long> {
    List<PerformanceCategory> findByName(String name);
}
