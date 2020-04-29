package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySalonId(Long salonId);
}
