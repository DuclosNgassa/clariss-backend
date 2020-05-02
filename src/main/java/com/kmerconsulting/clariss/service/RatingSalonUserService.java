package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.RatingAuthor;
import com.kmerconsulting.clariss.model.RatingSalonUser;
import com.kmerconsulting.clariss.repository.RatingSalonUserRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingSalonUserService {
    @Autowired
    RatingSalonUserRepository ratingSalonRepository;

    public RatingSalonUser save(RatingSalonUser ratingSalon) {
        return ratingSalonRepository.save(ratingSalon);
    }

    public RatingSalonUser update(RatingSalonUser ratingSalon) {
        return ratingSalonRepository.save(ratingSalon);
    }

    public List<RatingSalonUser> findAll() {
        return ratingSalonRepository.findAll();
    }

    public RatingSalonUser findById(Long id) {
        return ratingSalonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity RatingSalonUser with id: " + id + "not found"));
    }

    public List<RatingSalonUser> findByUserId(Long userId) {
        return ratingSalonRepository.findByUserId(userId);
    }

    public List<RatingSalonUser> findBySalonId(Long salonId) {
        return ratingSalonRepository.findBySalonId(salonId);
    }

    public List<RatingSalonUser> findByAuthor(RatingAuthor author) {
        return ratingSalonRepository.findByAuthor(author);
    }

    public void delete(Long id) {
        ratingSalonRepository.deleteById(id);
    }

}
