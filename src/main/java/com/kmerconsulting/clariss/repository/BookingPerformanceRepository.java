package com.kmerconsulting.clariss.repository;

import com.kmerconsulting.clariss.model.BookingPerformance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingPerformanceRepository extends JpaRepository<BookingPerformance, Long> {

    List<BookingPerformance> findByBookingId(Long bookingId);

    List<BookingPerformance> findByPerformanceId(Long performanceId);
}
