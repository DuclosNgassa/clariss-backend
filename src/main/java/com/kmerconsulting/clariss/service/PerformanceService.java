package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.Performance;
import com.kmerconsulting.clariss.repository.PerformanceRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {
    @Autowired
    PerformanceRepository performanceRepository;

    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    public Performance update(Performance performance) {
        return performanceRepository.save(performance);
    }

    public List<Performance> findAll() {
        return performanceRepository.findAll();
    }

    public List<Performance> findByStatus(GlobalStatus status) {
        return performanceRepository.findByStatus(status).orElse(null);
    }

    public Performance findById(Long id) {
        return performanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Performance with id: " + id + "not found"));
    }

    public List<Performance> findBySalonId(Long salonId) {
        return performanceRepository.findBySalonId(salonId);
    }

    public List<Performance> findByPerformanceUndercategoryId(Long performanceUndercategoryId) {
        return performanceRepository.findByPerformanceUndercategoryId(performanceUndercategoryId);
    }

    public void delete(Long id) {
        performanceRepository.deleteById(id);
    }

    public boolean isActive(Performance performance) {
        return performance.getStatus() == GlobalStatus.active;
    }

}
