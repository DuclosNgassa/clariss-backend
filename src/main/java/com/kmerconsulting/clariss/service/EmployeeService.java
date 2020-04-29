package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.Employee;
import com.kmerconsulting.clariss.repository.EmployeeRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Employee with id: " + id + "not found"));
    }

    public List<Employee> findBySalonId(Long salonId) {
        return employeeRepository.findBySalonId(salonId);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
