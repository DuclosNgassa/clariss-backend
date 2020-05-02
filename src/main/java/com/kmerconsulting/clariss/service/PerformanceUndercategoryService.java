package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.PerformanceUndercategory;
import com.kmerconsulting.clariss.repository.PerformanceUndercategoryRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceUndercategoryService {
    @Autowired
    PerformanceUndercategoryRepository performanceUndercategoryRepository;

    public PerformanceUndercategory save(PerformanceUndercategory performanceUndercategory) {
        return performanceUndercategoryRepository.save(performanceUndercategory);
    }

    public PerformanceUndercategory update(PerformanceUndercategory performanceUndercategory) {
        return performanceUndercategoryRepository.save(performanceUndercategory);
    }

    public List<PerformanceUndercategory> findAll() {
        return performanceUndercategoryRepository.findAll();
    }

    public PerformanceUndercategory findById(Long id) {
        return performanceUndercategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity PerformanceUndercategory with id: " + id + "not found"));
    }

    public List<PerformanceUndercategory> findByPerformanceCategoryId(Long performanceCategoryId) {
        return performanceUndercategoryRepository.findByPerformanceCategoryId(performanceCategoryId);
    }

    public void delete(Long id) {
        performanceUndercategoryRepository.deleteById(id);
    }

}
