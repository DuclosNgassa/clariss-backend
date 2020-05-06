package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.PerformanceUndercategory;
import com.kmerconsulting.clariss.service.PerformanceUndercategoryService;
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
@RequestMapping("/api/performanceUndercategory")
public class PerformanceUndercategoryController {

    @Autowired
    PerformanceUndercategoryService performanceUndercategoryService;

    @PostMapping()
    public ResponseEntity<PerformanceUndercategory> save(@Valid @RequestBody PerformanceUndercategory performanceUndercategory) throws Exception {
        PerformanceUndercategory createdPerformanceUndercategory = performanceUndercategoryService.save(performanceUndercategory);

        if (createdPerformanceUndercategory == null) {
            throw new Exception("Error while saving performanceUndercategory");
        }

        return ResponseEntity.ok(createdPerformanceUndercategory);
    }

    @GetMapping()
    public ResponseEntity<List<PerformanceUndercategory>> findAll() {
        List<PerformanceUndercategory> performanceUndercategorys = performanceUndercategoryService.findAll();

        if (performanceUndercategorys == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performanceUndercategorys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceUndercategory> findById(@PathVariable(value = "id") Long id) {
        PerformanceUndercategory performanceUndercategory = performanceUndercategoryService.findById(id);
        if (performanceUndercategory == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performanceUndercategory);
    }

    @GetMapping("/performanceCategory/{performanceCategoryId}")
    public ResponseEntity<List<PerformanceUndercategory>> findByPerformanceCategoryId(@PathVariable(value = "performanceCategoryId") Long performanceCategoryId) {
        List<PerformanceUndercategory> performanceUndercategorys = performanceUndercategoryService.findByPerformanceCategoryId(performanceCategoryId);
        if (performanceUndercategorys == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performanceUndercategorys);
    }

    /**
     * This method updates a performanceUndercategory based on his id and his details
     *
     * @param performanceUndercategoryDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceUndercategory> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PerformanceUndercategory performanceUndercategoryDetail) {
        PerformanceUndercategory performanceUndercategory = performanceUndercategoryService.findById(id);
        if (performanceUndercategory == null) {
            return ResponseEntity.notFound().build();
        }

        performanceUndercategory.setPerformanceCategoryId(performanceUndercategoryDetail.getPerformanceCategoryId());
        performanceUndercategory.setName(performanceUndercategoryDetail.getName());
        performanceUndercategory.setDescription(performanceUndercategoryDetail.getDescription());

        PerformanceUndercategory updatedPerformanceUndercategory = performanceUndercategoryService.update(performanceUndercategory);

        return ResponseEntity.ok(updatedPerformanceUndercategory);
    }

    /**
     * This method deletes a performanceUndercategory based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PerformanceUndercategory> delete(@PathVariable(value = "id") Long id) {
        PerformanceUndercategory performanceUndercategory = performanceUndercategoryService.findById(id);
        if (performanceUndercategory == null) {
            return ResponseEntity.notFound().build();
        }
        performanceUndercategoryService.delete(id);

        return ResponseEntity.ok(performanceUndercategory);
    }
}
