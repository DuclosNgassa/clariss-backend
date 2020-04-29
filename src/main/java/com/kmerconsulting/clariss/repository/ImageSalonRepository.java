package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.ImageSalon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageSalonRepository extends JpaRepository<ImageSalon, Long> {
    List<ImageSalon> findBySalonId(Long salonId);

    List<ImageSalon> findByImageUrl(String imageUrl);
}
