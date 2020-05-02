package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.RatingAuthor;
import com.kmerconsulting.clariss.model.RatingUserEmployee;
import com.kmerconsulting.clariss.repository.RatingUserEmployeeRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingUserEmployeeService {
    @Autowired
    RatingUserEmployeeRepository ratingUserEmployeeRepository;

    public RatingUserEmployee save(RatingUserEmployee ratingUserEmployee) {
        return ratingUserEmployeeRepository.save(ratingUserEmployee);
    }

    public RatingUserEmployee update(RatingUserEmployee ratingUserEmployee) {
        return ratingUserEmployeeRepository.save(ratingUserEmployee);
    }

    public List<RatingUserEmployee> findAll() {
        return ratingUserEmployeeRepository.findAll();
    }

    public RatingUserEmployee findById(Long id) {
        return ratingUserEmployeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity RatingUserEmployee with id: " + id + "not found"));
    }

    public List<RatingUserEmployee> findByUserId(Long userId) {
        return ratingUserEmployeeRepository.findByUserId(userId);
    }

    public List<RatingUserEmployee> findByEmployeeId(Long employeeId) {
        return ratingUserEmployeeRepository.findByEmployeeId(employeeId);
    }

    public List<RatingUserEmployee> findByAuthor(RatingAuthor author) {
        return ratingUserEmployeeRepository.findByAuthor(author);
    }

    public void delete(Long id) {
        ratingUserEmployeeRepository.deleteById(id);
    }

}
