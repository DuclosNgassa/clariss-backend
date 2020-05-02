package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.RatingPerformance;
import com.kmerconsulting.clariss.repository.RatingPerformanceRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingPerformanceService {
    @Autowired
    RatingPerformanceRepository ratingPerformanceRepository;

    public RatingPerformance save(RatingPerformance ratingPerformance) {
        return ratingPerformanceRepository.save(ratingPerformance);
    }

    public RatingPerformance update(RatingPerformance ratingPerformance) {
        return ratingPerformanceRepository.save(ratingPerformance);
    }

    public List<RatingPerformance> findAll() {
        return ratingPerformanceRepository.findAll();
    }

    public RatingPerformance findById(Long id) {
        return ratingPerformanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity RatingPerformance with id: " + id + "not found"));
    }

    public List<RatingPerformance> findByUserId(Long userId) {
        return ratingPerformanceRepository.findByUserId(userId);
    }

    public List<RatingPerformance> findByPerformanceId(Long performanceId) {
        return ratingPerformanceRepository.findByPerformanceId(performanceId);
    }

    public void delete(Long id) {
        ratingPerformanceRepository.deleteById(id);
    }

}
