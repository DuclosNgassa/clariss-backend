package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.RatingAuthor;
import com.kmerconsulting.clariss.model.RatingUserEmployee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingUserEmployeeRepository extends JpaRepository<RatingUserEmployee, Long> {
    List<RatingUserEmployee> findByEmployeeId(Long employeeId);

    List<RatingUserEmployee> findByUserId(Long userId);

    List<RatingUserEmployee> findByAuthor(RatingAuthor author);
}
