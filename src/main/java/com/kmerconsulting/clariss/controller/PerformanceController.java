package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.Performance;
import com.kmerconsulting.clariss.service.PerformanceService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    @Autowired
    PerformanceService performanceService;

    @PostMapping("/create")
    public ResponseEntity<Performance> create(@Valid @RequestBody Performance performance) throws Exception {
        Performance createdPerformance = performanceService.save(performance);

        if (createdPerformance == null) {
            throw new Exception("Error while saving performance");
        }

        return ResponseEntity.ok(createdPerformance);
    }

    @GetMapping()
    public ResponseEntity<List<Performance>> findAll() {
        List<Performance> performances = performanceService.findAll();

        if (performances == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Performance> findById(@PathVariable(value = "id") Long id) {
        Performance performance = performanceService.findById(id);
        if (performance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performance);
    }

    @GetMapping("/performanceUndercategory/{performanceUndercategoryId}")
    public ResponseEntity<List<Performance>> findByPerformanceUndercategoryId(@PathVariable(value = "performanceUndercategoryId") Long performanceUndercategoryId) {
        List<Performance> performances = performanceService.findByPerformanceUndercategoryId(performanceUndercategoryId);
        if (performances == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performances);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Performance>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<Performance> performances = performanceService.findBySalonId(salonId);
        if (performances == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performances);
    }

    /**
     * This method updates a performance based on his id and his details
     *
     * @param performanceDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Performance> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Performance performanceDetail) {
        Performance performance = performanceService.findById(id);
        if (performance == null) {
            return ResponseEntity.notFound().build();
        }

        performance.setDescription(performanceDetail.getDescription());
        performance.setName(performanceDetail.getName());
        performance.setDuration(performanceDetail.getDuration());
        performance.setMaxPrice(performanceDetail.getMaxPrice());
        performance.setMinPrice(performanceDetail.getMinPrice());
        performance.setPerformanceUndercategoryId(performanceDetail.getPerformanceUndercategoryId());
        performance.setPromoPrice(performanceDetail.getPromoPrice());
        performance.setSalonId(performanceDetail.getSalonId());
        performance.setRating(performanceDetail.getRating());

        Performance updatedPerformance = performanceService.update(performance);

        return ResponseEntity.ok(updatedPerformance);
    }

    /**
     * This method deletes a performance based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Performance> delete(@PathVariable(value = "id") Long id) {
        Performance performance = performanceService.findById(id);
        if (performance == null) {
            return ResponseEntity.notFound().build();
        }
        performanceService.delete(id);

        return ResponseEntity.ok(performance);
    }
}
