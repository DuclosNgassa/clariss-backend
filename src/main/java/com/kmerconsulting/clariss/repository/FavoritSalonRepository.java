package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.FavoritSalon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritSalonRepository extends JpaRepository<FavoritSalon, Long> {
    List<FavoritSalon> findBySalonId(Long salonId);

    List<FavoritSalon> findByUserId(Long userId);
}
