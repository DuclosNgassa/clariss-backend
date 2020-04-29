package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.RatingSalonUser;
import com.kmerconsulting.clariss.service.RatingSalonUserService;
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
@RequestMapping("/api/ratingSalonUser")
public class RatingSalonUserController {

    @Autowired
    RatingSalonUserService ratingSalonUserService;

    @PostMapping("/create")
    public ResponseEntity<RatingSalonUser> create(@Valid @RequestBody RatingSalonUser ratingSalonUser) throws Exception {
        ratingSalonUser.setCreatedAt(LocalDateTime.now());
        RatingSalonUser createdRatingSalonUser = ratingSalonUserService.save(ratingSalonUser);

        if (createdRatingSalonUser == null) {
            throw new Exception("Error while saving ratingSalonUser");
        }

        return ResponseEntity.ok(createdRatingSalonUser);
    }

    @GetMapping()
    public ResponseEntity<List<RatingSalonUser>> findAll() {
        List<RatingSalonUser> ratingSalonUsers = ratingSalonUserService.findAll();

        if (ratingSalonUsers == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingSalonUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingSalonUser> findById(@PathVariable(value = "id") Long id) {
        RatingSalonUser ratingSalonUser = ratingSalonUserService.findById(id);
        if (ratingSalonUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingSalonUser);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<RatingSalonUser>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<RatingSalonUser> ratingSalonUsers = ratingSalonUserService.findBySalonId(salonId);
        if (ratingSalonUsers == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingSalonUsers);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingSalonUser>> findByUserId(@PathVariable(value = "userId") Long userId) {
        List<RatingSalonUser> ratingSalonUsers = ratingSalonUserService.findByUserId(userId);
        if (ratingSalonUsers == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ratingSalonUsers);
    }

    /**
     * This method updates a ratingSalonUser based on his id and his details
     *
     * @param ratingSalonUserDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<RatingSalonUser> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RatingSalonUser ratingSalonUserDetail) {
        RatingSalonUser ratingSalonUser = ratingSalonUserService.findById(id);
        if (ratingSalonUser == null) {
            return ResponseEntity.notFound().build();
        }

        ratingSalonUser.setComment(ratingSalonUserDetail.getComment());
        ratingSalonUser.setSalonId(ratingSalonUserDetail.getSalonId());
        ratingSalonUser.setAuthor(ratingSalonUserDetail.getAuthor());
        ratingSalonUser.setScore(ratingSalonUserDetail.getScore());
        ratingSalonUser.setUserId(ratingSalonUserDetail.getUserId());

        RatingSalonUser updatedRatingSalonUser = ratingSalonUserService.update(ratingSalonUser);

        return ResponseEntity.ok(updatedRatingSalonUser);
    }

    /**
     * This method deletes a ratingSalonUser based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<RatingSalonUser> delete(@PathVariable(value = "id") Long id) {
        RatingSalonUser ratingSalonUser = ratingSalonUserService.findById(id);
        if (ratingSalonUser == null) {
            return ResponseEntity.notFound().build();
        }
        ratingSalonUserService.delete(id);

        return ResponseEntity.ok(ratingSalonUser);
    }
}
