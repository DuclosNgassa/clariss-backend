package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.Employee;
import com.kmerconsulting.clariss.service.EmployeeService;
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
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) throws Exception {
        Employee createdEmployee = employeeService.save(employee);

        if (createdEmployee == null) {
            throw new Exception("Error while saving employee");
        }

        return ResponseEntity.ok(createdEmployee);
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
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Employee>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<Employee> employees = employeeService.findBySalonId(salonId);
        if (employees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employees);
    }

    /**
     * This method updates a employee based on his id and his details
     *
     * @param employeeDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Employee employeeDetail) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        employee.setName(employeeDetail.getName());
        employee.setFunction(employeeDetail.getFunction());
        employee.setDescription(employeeDetail.getDescription());
        employee.setImageProfilUrl(employeeDetail.getImageProfilUrl());
        employee.setSalonId(employeeDetail.getSalonId());

        Employee updatedEmployee = employeeService.update(employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * This method deletes a employee based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable(value = "id") Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeService.delete(id);

        return ResponseEntity.ok(employee);
    }
}
