package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.GlobalStatus;
import com.kmerconsulting.clariss.model.ManagerSalon;
import com.kmerconsulting.clariss.model.Performance;
import com.kmerconsulting.clariss.model.Salon;
import com.kmerconsulting.clariss.repository.SalonRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalonService {
    @Autowired
    SalonRepository salonRepository;

    public Salon save(Salon salon) {
        return salonRepository.save(salon);
    }

    public Salon update(Salon salon) {
        return salonRepository.save(salon);
    }

    public List<Salon> findAll() {
        return salonRepository.findAll();
    }

    public Salon findById(Long id) {
        return salonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Salon with id: " + id + "not found"));
    }

    public void delete(Long id) {
        salonRepository.deleteById(id);
    }

    public boolean isActive(Salon salon) {
        return salon.getStatus() == GlobalStatus.active;
    }

    public boolean isUserSalonOwner(Salon salon, List<ManagerSalon> managerSalons) {
        return managerSalons.stream().anyMatch(managerSalon -> managerSalon.getSalonId().equals(salon.getId()));
    }

}
