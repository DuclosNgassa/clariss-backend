package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.ManagerSalon;
import com.kmerconsulting.clariss.repository.ManagerSalonRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerSalonService {
    @Autowired
    ManagerSalonRepository managerSalonRepository;

    public ManagerSalon save(ManagerSalon managerSalon) {
        return managerSalonRepository.save(managerSalon);
    }

    public ManagerSalon update(ManagerSalon managerSalon) {
        return managerSalonRepository.save(managerSalon);
    }

    public List<ManagerSalon> findAll() {
        return managerSalonRepository.findAll();
    }

    public ManagerSalon findById(Long id) {
        return managerSalonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity ManagerSalon with id: " + id + "not found"));
    }

    public List<ManagerSalon> findByUserId(Long userId) {
        return managerSalonRepository.findByUserId(userId);
    }

    public List<ManagerSalon> findBySalonId(Long salonId) {
        return managerSalonRepository.findBySalonId(salonId);
    }

    public void delete(Long id) {
        managerSalonRepository.deleteById(id);
    }

}
