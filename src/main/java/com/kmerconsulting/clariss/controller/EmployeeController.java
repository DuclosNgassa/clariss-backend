package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.Employee;
import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.ManagerSalon;
import com.kmerconsulting.clariss.model.Salon;
import com.kmerconsulting.clariss.service.EmployeeService;
import com.kmerconsulting.clariss.service.ManagerSalonService;
import com.kmerconsulting.clariss.service.SalonService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    ManagerSalonService managerSalonService;
    @Autowired
    SalonService salonService;

    @PostMapping("/{managerId}")
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee, @PathVariable(value = "managerId") Long managerId) throws Exception {
        Salon salon = salonService.findById(employee.getSalonId());
        if (salon == null || !salonService.isActive(salon)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salon, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        employee.setCreatedAt(LocalDateTime.now());
        employee.setStatus(GlobalStatus.active);
        Employee createdEmployee = employeeService.save(employee);

        if (createdEmployee == null) {
            throw new Exception("Error while saving employee");
        }

        return ResponseEntity.ok(createdEmployee);
    }

    @PostMapping("/uploadProfilPicture")
    public ResponseEntity<String> uploadImage(@RequestParam("profilPicture") MultipartFile profilPicture) {
        String profilPictureUrl = "";
        try {
            profilPictureUrl = employeeService.saveProfilPicture(profilPicture);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO log error
            profilPictureUrl = "error";
        }
        return ResponseEntity.ok(profilPictureUrl);
    }


    @GetMapping()
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = employeeService.findAll();

        if (employees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable(value = "id") Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null || !employeeService.isActive(employee)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Employee>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        Salon salon = salonService.findById(salonId);
        if (salon == null || !salonService.isActive(salon)) {
            return ResponseEntity.notFound().build();
        }

        List<Employee> employees = employeeService.findBySalonId(salonId);
        if (employees == null) {
            return ResponseEntity.notFound().build();
        }

        List<Employee> employeesActive = employees.stream().filter(employeeService::isActive).collect(Collectors.toList());
        return ResponseEntity.ok(employeesActive);
    }

    /**
     * This method updates a employee based on his id, the managerId and his details
     *
     * @param employeeDetail
     * @param id
     * @param managerId
     * @return
     */
    @PutMapping("/{id}/{managerId}")
    public ResponseEntity<Employee> update(@PathVariable(value = "id") Long id, @PathVariable(value = "managerId") Long managerId, @Valid @RequestBody Employee employeeDetail) {
        Employee employee = employeeService.findById(id);
        if (employee == null || !employeeService.isActive(employee)) {
            return ResponseEntity.notFound().build();
        }

        Salon salonFound = salonService.findById(employee.getSalonId());
        if (salonFound == null || !salonService.isActive(salonFound)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salonFound, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        employee.setName(employeeDetail.getName());
        employee.setPosition(employeeDetail.getPosition());
        employee.setDescription(employeeDetail.getDescription());
        employee.setPicture(employeeDetail.getPicture());
        employee.setSalonId(employeeDetail.getSalonId());

        Employee updatedEmployee = employeeService.update(employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * This method deletes a employee based on his id and the managerId
     *
     * @param id
     * @param managerId
     * @return
     */
    @DeleteMapping("/{id}/{managerId}")
    public ResponseEntity<Employee> delete(@PathVariable(value = "id") Long id, @PathVariable(value = "managerId") Long managerId) {
        Employee employee = employeeService.findById(id);
        if (employee == null || !employeeService.isActive(employee)) {
            return ResponseEntity.notFound().build();
        }

        Salon salonFound = salonService.findById(employee.getSalonId());
        if (salonFound == null || !salonService.isActive(salonFound)) {
            return ResponseEntity.notFound().build();
        }

        List<ManagerSalon> managerSalons = managerSalonService.findByUserId(managerId);

        if (managerSalons == null || !salonService.isUserSalonOwner(salonFound, managerSalons)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        employee.setStatus(GlobalStatus.deleted);

        employeeService.update(employee);
        if (!StringUtils.isEmpty(employee.getPicture())) {
            employeeService.deleteFile(employee.getPicture());
        }

        return ResponseEntity.ok(employee);
    }
}
