package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.ManagerSalon;
import com.kmerconsulting.clariss.model.Performance;
import com.kmerconsulting.clariss.model.Salon;
import com.kmerconsulting.clariss.model.User;
import com.kmerconsulting.clariss.model.UserRole;
import com.kmerconsulting.clariss.service.ManagerSalonService;
import com.kmerconsulting.clariss.service.PerformanceService;
import com.kmerconsulting.clariss.service.SalonService;
import com.kmerconsulting.clariss.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
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
@RequestMapping("/api/salon")
public class SalonController {
    @Autowired
    SalonService salonService;
    @Autowired
    UserService userService;
    @Autowired
    ManagerSalonService managerSalonService;
    @Autowired
    PerformanceService performanceService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Salon> create(@Valid @RequestBody Salon salon, @PathVariable(value = "userId") Long userId) throws Exception {
        if (isUserManager(userId)) {
            salon.setCreatedAt(LocalDateTime.now());
            salon.setViews(0);
            Salon createdSalon = salonService.save(salon);
            if (createdSalon == null) {
                throw new Exception("Error while saving salon");
            }

            saveManagerSalon(userId, createdSalon.getId());

            return ResponseEntity.ok(createdSalon);
        }
        throw new Exception("The user with the id " + userId + "is not a manager");
    }

    private void saveManagerSalon(@PathVariable("userId") Long userId, Long salonId) {
        ManagerSalon managerSalon = new ManagerSalon();
        managerSalon.setCreatedAt(LocalDateTime.now());
        managerSalon.setUserId(userId);
        managerSalon.setSalonId(salonId);

        managerSalonService.save(managerSalon);
    }

    /**
     * This method updates a salon based on his id and his details
     *
     * @param salon
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Salon> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Salon salon) throws Exception {
        Salon salonFound = salonService.findById(id);

        if (salonFound == null) {
            throw new Exception("Error while saving salon");
        }
        salonFound.setAddressId(salon.getAddressId());
        salonFound.setDescription(salon.getDescription());
        salonFound.setEmail(salon.getEmail());
        salonFound.setName(salon.getName());
        salonFound.setPhone(salon.getPhone());
        salonFound.setRating(salon.getRating());
        salonFound.setStatus(GlobalStatus.active);
        salonFound.setViews(salon.getViews());
        salonFound.setCreatedAt(salon.getCreatedAt());

        Salon updatedSalon = salonService.update(salonFound);

        return ResponseEntity.ok(updatedSalon);
    }

    /**
     * get all salons
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Salon>> findAll() {
        List<Salon> salones = salonService.findAll();
        if (salones == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(salones);
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Salon>> findByManagerId(@PathVariable(value = "managerId") Long managerId) {
        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);
        if (managerSalons == null) {
            return ResponseEntity.notFound().build();
        }
        List<Salon> salons = new ArrayList<>();
        managerSalons.stream().forEach((managerSalon) ->{
            Salon salon = salonService.findById(managerSalon.getSalonId());
            salons.add(salon);
        });

        return ResponseEntity.ok(salons);
    }

    @GetMapping("/performanceUndercategory/{performanceUndercategory}")
    public ResponseEntity<List<Salon>> findByPerformanceUndercategory(@PathVariable(value = "performanceUndercategory") Long performanceUndercategory) {
        List<Performance> performances = performanceService.findByPerformanceUndercategoryId(performanceUndercategory);

        List<Salon> salons = salonService.findAll();

        final Set<Salon> selectedSalons = new HashSet<>();

        if(performances != null){
            performances.forEach((performance) ->{
                Optional<Salon> salonOptional = salons.stream().filter((salonItem) -> salonItem.getId() == performance.getSalonId()).findFirst();
                if(salonOptional.isPresent()){
                    selectedSalons.add(salonOptional.get());
                }
            });
        }

        return ResponseEntity.ok(selectedSalons.stream().collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Salon> findById(@PathVariable(value = "id") Long id) {
        Salon salon = salonService.findById(id);
        if (salon == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(salon);
    }

    /**
     * This method deletes a salon based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Salon> delete(@PathVariable(value = "id") Long id) throws Exception {
        Salon salon = salonService.findById(id);
        if (salon == null) {
            throw new Exception("Error while saving salon");
        }

        salonService.delete(id);

        return ResponseEntity.ok(salon);
    }

    private boolean isUserManager(Long userId) {
        User user = userService.findById(userId);
        return user != null && user.getRole() == UserRole.manager;
    }

}
