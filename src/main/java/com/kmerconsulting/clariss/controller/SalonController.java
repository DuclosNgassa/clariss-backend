package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.Employee;
import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.ImageSalon;
import com.kmerconsulting.clariss.model.ManagerSalon;
import com.kmerconsulting.clariss.model.Performance;
import com.kmerconsulting.clariss.model.Salon;
import com.kmerconsulting.clariss.service.EmployeeService;
import com.kmerconsulting.clariss.service.ImageSalonService;
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
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ImageSalonService imageSalonService;

    @PostMapping("/{managerId}")
    public ResponseEntity<Salon> save(@Valid @RequestBody Salon salon, @PathVariable(value = "managerId") Long managerId) throws Exception {
        if (userService.isUserManager(managerId)) {
            salon.setCreatedAt(LocalDateTime.now());
            salon.setViews(0);
            Salon createdSalon = salonService.save(salon);
            if (createdSalon == null) {
                throw new Exception("Error while saving salon");
            }

            saveManagerSalon(managerId, createdSalon.getId());

            return ResponseEntity.ok(createdSalon);
        }
        throw new Exception("The user with the id " + managerId + "is not a manager");
    }

    /**
     * This method updates a salon based on his id and his details
     *
     * @param salon
     * @param id
     * @return
     */
    @PutMapping("/{id}/{managerId}")
    public ResponseEntity<Salon> update(@PathVariable(value = "id") Long id, @PathVariable(value = "managerId") Long managerId, @Valid @RequestBody Salon salon) throws Exception {
        Salon salonFound = salonService.findById(id);
        if (salonFound == null || !salonService.isActive(salonFound)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salon, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        salonFound.setAddressId(salon.getAddressId());
        salonFound.setDescription(salon.getDescription());
        salonFound.setEmail(salon.getEmail());
        salonFound.setName(salon.getName());
        salonFound.setPhone(salon.getPhone());
        salonFound.setRating(salon.getRating());
        salonFound.setViews(salon.getViews());

        Salon updatedSalon = salonService.update(salonFound);

        if (updatedSalon == null) {
            throw new Exception("Error while updating salon");
        }

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
        managerSalons.stream().forEach((managerSalon) -> {
            Salon salon = salonService.findById(managerSalon.getSalonId());
            salons.add(salon);
        });

        List<Salon> salonResults = managerSalons.stream()
                .map(item -> salonService.findById(item.getSalonId()))
                .filter(salonService::isActive)
                .collect(Collectors.toList());

        return ResponseEntity.ok(salonResults);
    }

    @GetMapping("/performanceUndercategory/{performanceUndercategory}")
    public ResponseEntity<List<Salon>> findByPerformanceUndercategory(@PathVariable(value = "performanceUndercategory") Long performanceUndercategory) {
        List<Performance> performances = performanceService.findByPerformanceUndercategoryId(performanceUndercategory);

        List<Salon> salons = salonService.findAllActive();

        final Set<Salon> salonHashSet = new HashSet<>();

        if (performances != null) {
            performances.forEach((performance) -> {
                Optional<Salon> salonOptional = salons.stream().filter(salonItem -> salonItem.getId() == performance.getSalonId()).findFirst();
                if (salonOptional.isPresent()) {
                    salonHashSet.add(salonOptional.get());
                }
            });
        }

        List<Salon> selectedSalonList = salonHashSet.stream().collect(Collectors.toList());
        return ResponseEntity.ok(selectedSalonList);
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
     * This method deletes (sets status to deleted) a salon based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/{managerId}")
    public ResponseEntity<Salon> delete(@PathVariable(value = "id") Long id, @PathVariable(value = "managerId") Long managerId) {
        Salon salon = salonService.findById(id);
        if (salon == null || !salonService.isActive(salon)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salon, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        salon.setStatus(GlobalStatus.deleted);
        salonService.update(salon);

        deletePerformance(id);
        deleteEmployee(id);
        deleteImageSalon(id);

        //TODO delete opening time


        return ResponseEntity.ok(salon);
    }

    private void deleteImageSalon(Long salonId) {
        List<ImageSalon> imageSalons = imageSalonService.findBySalonId(salonId);
        imageSalons.stream().forEach(imageSalon -> imageSalonService.deleteFile(imageSalon.getImageUrl()));
    }

    private void deletePerformance(Long salonId) {
        List<Performance> performances = performanceService.findBySalonId(salonId);
        performances.forEach(this::deletePerformance);
    }

    private void deletePerformance(Performance performance) {
        performance.setStatus(GlobalStatus.deleted);
        performanceService.update(performance);
    }

    private void deleteEmployee(Long salonId) {
        List<Employee> employees = employeeService.findBySalonId(salonId);
        employees.forEach(this::deleteEmployee);
    }

    private void deleteEmployee(Employee employee) {
        employee.setStatus(GlobalStatus.deleted);
        employeeService.update(employee);
        employeeService.deleteFile(employee.getPicture());

    }

    private void saveManagerSalon(Long userId, Long salonId) {
        ManagerSalon managerSalon = new ManagerSalon();
        managerSalon.setCreatedAt(LocalDateTime.now());
        managerSalon.setUserId(userId);
        managerSalon.setSalonId(salonId);

        managerSalonService.save(managerSalon);
    }

}
