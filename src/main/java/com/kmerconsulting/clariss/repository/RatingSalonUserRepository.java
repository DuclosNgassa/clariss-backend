package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.RatingAuthor;
import com.kmerconsulting.clariss.model.RatingSalonUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingSalonUserRepository extends JpaRepository<RatingSalonUser, Long> {
    List<RatingSalonUser> findBySalonId(Long salonId);

    List<RatingSalonUser> findByUserId(Long userId);

    List<RatingSalonUser> findByAuthor(RatingAuthor author);
}
