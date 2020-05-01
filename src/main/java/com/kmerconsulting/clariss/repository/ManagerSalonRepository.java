package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.ManagerSalon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerSalonRepository extends JpaRepository<ManagerSalon, Long> {
    List<ManagerSalon> findBySalonId(Long salonId);

    List<ManagerSalon> findByUserId(Long userId);
}
