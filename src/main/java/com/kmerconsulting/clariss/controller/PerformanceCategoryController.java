package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.PerformanceCategory;
import com.kmerconsulting.clariss.service.PerformanceCategoryService;
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
@RequestMapping("/api/performanceCategory")
public class PerformanceCategoryController {

    @Autowired
    PerformanceCategoryService performanceCategoryService;

    @PostMapping()
    public ResponseEntity<PerformanceCategory> save(@Valid @RequestBody PerformanceCategory performanceCategory) throws Exception {
        PerformanceCategory createdPerformanceCategory = performanceCategoryService.save(performanceCategory);

        if (createdPerformanceCategory == null) {
            throw new Exception("Error while saving performanceCategory");
        }

        return ResponseEntity.ok(createdPerformanceCategory);
    }

    @GetMapping()
    public ResponseEntity<List<PerformanceCategory>> findAll() {
        List<PerformanceCategory> performanceCategorys = performanceCategoryService.findAll();

        if (performanceCategorys == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performanceCategorys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceCategory> findById(@PathVariable(value = "id") Long id) {
        PerformanceCategory performanceCategory = performanceCategoryService.findById(id);
        if (performanceCategory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performanceCategory);
    }

    /**
     * This method updates a performanceCategory based on his id and his details
     *
     * @param performanceCategoryDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceCategory> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PerformanceCategory performanceCategoryDetail) {
        PerformanceCategory performanceCategory = performanceCategoryService.findById(id);
        if (performanceCategory == null) {
            return ResponseEntity.notFound().build();
        }

        performanceCategory.setImageUrl(performanceCategoryDetail.getImageUrl());
        performanceCategory.setName(performanceCategoryDetail.getName());

        PerformanceCategory updatedPerformanceCategory = performanceCategoryService.update(performanceCategory);

        return ResponseEntity.ok(updatedPerformanceCategory);
    }

    /**
     * This method deletes a performanceCategory based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PerformanceCategory> delete(@PathVariable(value = "id") Long id) {
        PerformanceCategory performanceCategory = performanceCategoryService.findById(id);
        if (performanceCategory == null) {
            return ResponseEntity.notFound().build();
        }
        performanceCategoryService.delete(id);

        return ResponseEntity.ok(performanceCategory);
    }
}
