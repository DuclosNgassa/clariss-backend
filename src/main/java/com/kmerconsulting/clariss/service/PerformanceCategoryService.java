package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.PerformanceCategory;
import com.kmerconsulting.clariss.repository.PerformanceCategoryRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceCategoryService {
    @Autowired
    PerformanceCategoryRepository performanceCategoryRepository;
    
    public PerformanceCategory save(PerformanceCategory performanceCategory) {
        return performanceCategoryRepository.save(performanceCategory);
    }

    public PerformanceCategory update(PerformanceCategory performanceCategory) {
        return performanceCategoryRepository.save(performanceCategory);
    }

    public List<PerformanceCategory> findAll() {
        return performanceCategoryRepository.findAll();
    }

    public PerformanceCategory findById(Long id) {
        return performanceCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity PerformanceCategory with id: " + id + "not found"));
    }

    public List<PerformanceCategory> findByName(String name) {
        return performanceCategoryRepository.findByName(name);
    }

    public void delete(Long id) {
        performanceCategoryRepository.deleteById(id);
    }

}
