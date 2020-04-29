package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.FavoritSalon;
import com.kmerconsulting.clariss.repository.FavoritSalonRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritSalonService {
    @Autowired
    FavoritSalonRepository favoritSalonRepository;
    
    public FavoritSalon save(FavoritSalon favoritSalon) {
        return favoritSalonRepository.save(favoritSalon);
    }

    public FavoritSalon update(FavoritSalon favoritSalon) {
        return favoritSalonRepository.save(favoritSalon);
    }

    public List<FavoritSalon> findAll() {
        return favoritSalonRepository.findAll();
    }

    public FavoritSalon findById(Long id) {
        return favoritSalonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity FavoritSalon with id: " + id + "not found"));
    }

    public List<FavoritSalon> findBySalonId(Long salonId) {
        return favoritSalonRepository.findBySalonId(salonId);
    }

    public List<FavoritSalon> findByUserId(Long userId) {
        return favoritSalonRepository.findByUserId(userId);
    }

    public void delete(Long id) {
        favoritSalonRepository.deleteById(id);
    }

}
