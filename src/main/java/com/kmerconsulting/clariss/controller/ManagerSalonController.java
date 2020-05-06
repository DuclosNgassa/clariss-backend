package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.ManagerSalon;
import com.kmerconsulting.clariss.model.User;
import com.kmerconsulting.clariss.model.UserRole;
import com.kmerconsulting.clariss.service.ManagerSalonService;
import com.kmerconsulting.clariss.service.UserService;
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
@RequestMapping("/api/managerSalon")
public class ManagerSalonController {
    @Autowired
    ManagerSalonService managerSalonService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<ManagerSalon> save(@Valid @RequestBody ManagerSalon managerSalon) throws Exception {

        if (isUserManager(managerSalon.getUserId())) {
            managerSalon.setCreatedAt(LocalDateTime.now());
            ManagerSalon createdManagerSalon = managerSalonService.save(managerSalon);

            if (createdManagerSalon == null) {
                throw new Exception("Error while saving managerSalon");
            }

            return ResponseEntity.ok(createdManagerSalon);
        }
        throw new Exception("The user with the id " + managerSalon.getUserId() + "is not a manager");
    }

    @GetMapping()
    public ResponseEntity<List<ManagerSalon>> findAll() {
        List<ManagerSalon> managerSalons = managerSalonService.findAll();

        if (managerSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(managerSalons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerSalon> findById(@PathVariable(value = "id") Long id) {
        ManagerSalon managerSalon = managerSalonService.findById(id);
        if (managerSalon == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(managerSalon);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<ManagerSalon>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<ManagerSalon> managerSalons = managerSalonService.findBySalonId(salonId);
        if (managerSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(managerSalons);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ManagerSalon>> findByUserId(@PathVariable(value = "userId") Long userId) {
        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(userId);
        if (managerSalons == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(managerSalons);
    }

    /**
     * This method updates a managerSalon based on his id and his details
     *
     * @param managerSalonDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ManagerSalon> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ManagerSalon managerSalonDetail) throws Exception {

        if (isUserManager(managerSalonDetail.getUserId())) {
            ManagerSalon managerSalon = managerSalonService.findById(id);
            if (managerSalon == null) {
                return ResponseEntity.notFound().build();
            }

            managerSalon.setComment(managerSalonDetail.getComment());
            managerSalon.setSalonId(managerSalonDetail.getSalonId());
            managerSalon.setUserId(managerSalonDetail.getUserId());

            ManagerSalon updatedManagerSalon = managerSalonService.update(managerSalon);

            return ResponseEntity.ok(updatedManagerSalon);
        }
        throw new Exception("The user with the id " + managerSalonDetail.getUserId() + "is not a manager");
    }

    /**
     * This method deletes a managerSalon based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ManagerSalon> delete(@PathVariable(value = "id") Long id) {
        ManagerSalon managerSalon = managerSalonService.findById(id);
        if (managerSalon == null) {
            return ResponseEntity.notFound().build();
        }
        managerSalonService.delete(id);

        return ResponseEntity.ok(managerSalon);
    }

    private boolean isUserManager(Long userId) {
        User user = userService.findById(userId);
        return user != null && user.getRole() == UserRole.manager;
    }
}
