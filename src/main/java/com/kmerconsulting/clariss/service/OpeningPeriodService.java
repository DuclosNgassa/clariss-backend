package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.OpeningPeriod;
import com.kmerconsulting.clariss.repository.OpeningPeriodRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpeningPeriodService {
    @Autowired
    OpeningPeriodRepository openingPeriodRepository;

    public OpeningPeriod save(OpeningPeriod openingPeriod) {
        return openingPeriodRepository.save(openingPeriod);
    }

    public OpeningPeriod update(OpeningPeriod openingPeriod) {
        return openingPeriodRepository.save(openingPeriod);
    }

    public List<OpeningPeriod> findAll() {
        return openingPeriodRepository.findAll();
    }

    public List<OpeningPeriod> findBySalonId(Long salonId) {
        return openingPeriodRepository.findBySalonId(salonId);
    }

    public OpeningPeriod findById(Long id) {
        return openingPeriodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity OpeningPeriod with id: " + id + "not found"));
    }

    public void delete(Long id) {
        openingPeriodRepository.deleteById(id);
    }

}
