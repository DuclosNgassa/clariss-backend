package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.Employee;
import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.repository.EmployeeRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeService {
    private static String uploadDirectory = System.getProperty("user.dir") + "/upload/employeeProfilPicture";
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    FileService fileService;

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

    public String saveProfilPicture(MultipartFile profilPicture) throws Exception {
        byte[] bytes = profilPicture.getBytes();
        Path path = Paths.get(uploadDirectory, profilPicture.getOriginalFilename());
        Files.write(path, bytes);
        return profilPicture.getOriginalFilename();
    }

    public void deleteFile(String fileName) {
        fileService.deleteFile(fileName);
    }

    public boolean isActive(Employee employee) {
        return employee.getStatus() == GlobalStatus.active;
    }

}
