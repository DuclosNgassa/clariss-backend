package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.OpeningPeriod;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpeningPeriodRepository extends JpaRepository<OpeningPeriod, Long> {
    List<OpeningPeriod> findBySalonId(Long salonId);
}