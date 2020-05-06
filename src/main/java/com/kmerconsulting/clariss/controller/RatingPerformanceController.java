package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.RatingPerformance;
import com.kmerconsulting.clariss.service.RatingPerformanceService;
import java.time.LocalDateTime;
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
@RequestMapping("/api/ratingPerformance")
public class RatingPerformanceController {

    @Autowired
    RatingPerformanceService ratingPerformanceService;

    @PostMapping()
    public ResponseEntity<RatingPerformance> save(@Valid @RequestBody RatingPerformance ratingPerformance) throws Exception {
        ratingPerformance.setCreatedAt(LocalDateTime.now());
        RatingPerformance createdRatingPerformance = ratingPerformanceService.save(ratingPerformance);

        if (createdRatingPerformance == null) {
            throw new Exception("Error while saving ratingPerformance");
        }

        return ResponseEntity.ok(createdRatingPerformance);
    }

    @GetMapping()
    public ResponseEntity<List<RatingPerformance>> findAll() {
        List<RatingPerformance> ratingPerformances = ratingPerformanceService.findAll();

        if (ratingPerformances == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingPerformances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingPerformance> findById(@PathVariable(value = "id") Long id) {
        RatingPerformance ratingPerformance = ratingPerformanceService.findById(id);
        if (ratingPerformance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingPerformance);
    }

    @GetMapping("/performance/{performanceId}")
    public ResponseEntity<List<RatingPerformance>> findByPerformanceId(@PathVariable(value = "performanceId") Long performanceId) {
        List<RatingPerformance> ratingPerformances = ratingPerformanceService.findByPerformanceId(performanceId);
        if (ratingPerformances == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingPerformances);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingPerformance>> findByUserId(@PathVariable(value = "userId") Long userId) {
        List<RatingPerformance> ratingPerformances = ratingPerformanceService.findByUserId(userId);
        if (ratingPerformances == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingPerformances);
    }

    /**
     * This method updates a ratingPerformance based on his id and his details
     *
     * @param ratingPerformanceDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<RatingPerformance> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RatingPerformance ratingPerformanceDetail) {
        RatingPerformance ratingPerformance = ratingPerformanceService.findById(id);
        if (ratingPerformance == null) {
            return ResponseEntity.notFound().build();
        }

        ratingPerformance.setComment(ratingPerformanceDetail.getComment());
        ratingPerformance.setPerformanceId(ratingPerformanceDetail.getPerformanceId());
        ratingPerformance.setScore(ratingPerformanceDetail.getScore());
        ratingPerformance.setUserId(ratingPerformanceDetail.getUserId());

        RatingPerformance updatedRatingPerformance = ratingPerformanceService.update(ratingPerformance);

        return ResponseEntity.ok(updatedRatingPerformance);
    }

    /**
     * This method deletes a ratingPerformance based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<RatingPerformance> delete(@PathVariable(value = "id") Long id) {
        RatingPerformance ratingPerformance = ratingPerformanceService.findById(id);
        if (ratingPerformance == null) {
            return ResponseEntity.notFound().build();
        }
        ratingPerformanceService.delete(id);

        return ResponseEntity.ok(ratingPerformance);
    }
}
