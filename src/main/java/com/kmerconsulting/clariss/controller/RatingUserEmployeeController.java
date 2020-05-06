package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.RatingUserEmployee;
import com.kmerconsulting.clariss.service.RatingUserEmployeeService;
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
@RequestMapping("/api/ratingUserEmployee")
public class RatingUserEmployeeController {

    @Autowired
    RatingUserEmployeeService ratingUserEmployeeService;

    @PostMapping()
    public ResponseEntity<RatingUserEmployee> save(@Valid @RequestBody RatingUserEmployee ratingUserEmployee) throws Exception {
        ratingUserEmployee.setCreatedAt(LocalDateTime.now());
        RatingUserEmployee createdRatingUserEmployee = ratingUserEmployeeService.save(ratingUserEmployee);

        if (createdRatingUserEmployee == null) {
            throw new Exception("Error while saving ratingUserEmployee");
        }

        return ResponseEntity.ok(createdRatingUserEmployee);
    }

    @GetMapping()
    public ResponseEntity<List<RatingUserEmployee>> findAll() {
        List<RatingUserEmployee> ratingUserEmployees = ratingUserEmployeeService.findAll();

        if (ratingUserEmployees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingUserEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingUserEmployee> findById(@PathVariable(value = "id") Long id) {
        RatingUserEmployee ratingUserEmployee = ratingUserEmployeeService.findById(id);
        if (ratingUserEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingUserEmployee);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<RatingUserEmployee>> findByEmployeeId(@PathVariable(value = "employeeId") Long employeeId) {
        List<RatingUserEmployee> ratingUserEmployees = ratingUserEmployeeService.findByEmployeeId(employeeId);
        if (ratingUserEmployees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingUserEmployees);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingUserEmployee>> findByUserId(@PathVariable(value = "userId") Long userId) {
        List<RatingUserEmployee> ratingUserEmployees = ratingUserEmployeeService.findByUserId(userId);
        if (ratingUserEmployees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingUserEmployees);
    }

    /**
     * This method updates a ratingUserEmployee based on his id and his details
     *
     * @param ratingUserEmployeeDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<RatingUserEmployee> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RatingUserEmployee ratingUserEmployeeDetail) {
        RatingUserEmployee ratingUserEmployee = ratingUserEmployeeService.findById(id);
        if (ratingUserEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        ratingUserEmployee.setComment(ratingUserEmployeeDetail.getComment());
        ratingUserEmployee.setEmployeeId(ratingUserEmployeeDetail.getEmployeeId());
        ratingUserEmployee.setAuthor(ratingUserEmployeeDetail.getAuthor());
        ratingUserEmployee.setScore(ratingUserEmployeeDetail.getScore());
        ratingUserEmployee.setUserId(ratingUserEmployeeDetail.getUserId());

        RatingUserEmployee updatedRatingUserEmployee = ratingUserEmployeeService.update(ratingUserEmployee);

        return ResponseEntity.ok(updatedRatingUserEmployee);
    }

    /**
     * This method deletes a ratingUserEmployee based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<RatingUserEmployee> delete(@PathVariable(value = "id") Long id) {
        RatingUserEmployee ratingUserEmployee = ratingUserEmployeeService.findById(id);
        if (ratingUserEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        ratingUserEmployeeService.delete(id);

        return ResponseEntity.ok(ratingUserEmployee);
    }
}
