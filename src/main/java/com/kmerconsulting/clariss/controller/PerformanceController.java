package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.ManagerSalon;
import com.kmerconsulting.clariss.model.Performance;
import com.kmerconsulting.clariss.model.Salon;
import com.kmerconsulting.clariss.service.ManagerSalonService;
import com.kmerconsulting.clariss.service.PerformanceService;
import com.kmerconsulting.clariss.service.SalonService;
import com.kmerconsulting.clariss.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    ManagerSalonService managerSalonService;
    @Autowired
    SalonService salonService;
    @Autowired
    UserService userService;

    @PostMapping("/{managerId}")
    public ResponseEntity<Performance> save(@Valid @RequestBody Performance performance, @PathVariable(value = "managerId") Long managerId) throws Exception {
        Salon salon = salonService.findById(performance.getSalonId());
        if (salon == null || !salonService.isActive(salon)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salon, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        performance.setStatus(GlobalStatus.active);
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

    @GetMapping("/active")
    public ResponseEntity<List<Performance>> findAllActive() {
        List<Performance> performances = performanceService.findByStatus(GlobalStatus.active);

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

        List<Performance> performancesActive = performances.stream().filter(performanceService::isActive).collect(Collectors.toList());
        return ResponseEntity.ok(performancesActive);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Performance>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<Performance> performances = performanceService.findBySalonId(salonId);
        if (performances == null) {
            return ResponseEntity.notFound().build();
        }
        List<Performance> performancesActive = performances.stream().filter(performanceService::isActive).collect(Collectors.toList());
        return ResponseEntity.ok(performancesActive);
    }

    /**
     * This method updates a performance based on his id and his details
     *
     * @param performanceDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}/{managerId}")
    public ResponseEntity<Performance> update(@PathVariable(value = "id") Long id, @PathVariable(value = "managerId") Long managerId, @Valid @RequestBody Performance performanceDetail) {
        Performance performance = performanceService.findById(id);
        if (performance == null || !performanceService.isActive(performance)) {
            return ResponseEntity.notFound().build();
        }

        Salon salonFound = salonService.findById(performance.getSalonId());
        if (salonFound == null || !salonService.isActive(salonFound)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salonFound, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        performance.setDescription(performanceDetail.getDescription());
        performance.setName(performanceDetail.getName());
        performance.setDuration(performanceDetail.getDuration());
        performance.setMaxPrice(performanceDetail.getMaxPrice());
        performance.setMinPrice(performanceDetail.getMinPrice());
        performance.setPerformanceUndercategoryId(performanceDetail.getPerformanceUndercategoryId());
        performance.setPromoPrice(performanceDetail.getPromoPrice());
        performance.setSalonId(performanceDetail.getSalonId());

        Performance updatedPerformance = performanceService.update(performance);

        return ResponseEntity.ok(updatedPerformance);
    }

    /**
     * This method deletes (sets status to deleted) a performance based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/{managerId}")
    public ResponseEntity<Performance> delete(@PathVariable(value = "id") Long id, @PathVariable(value = "managerId") Long managerId) {

        Performance performance = performanceService.findById(id);
        if (performance == null || !performanceService.isActive(performance)) {
            return ResponseEntity.notFound().build();
        }

        Salon salon = salonService.findById(performance.getSalonId());
        if (salon == null || !salonService.isActive(salon)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salon, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        performance.setStatus(GlobalStatus.deleted);
        performanceService.update(performance);

        return ResponseEntity.ok(performance);
    }

}
