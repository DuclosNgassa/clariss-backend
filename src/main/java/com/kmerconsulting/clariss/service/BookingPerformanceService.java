package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.BookingPerformance;
import com.kmerconsulting.clariss.repository.BookingPerformanceRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingPerformanceService {
    @Autowired
    BookingPerformanceRepository bookingPerformanceRepository;
    
    public BookingPerformance save(BookingPerformance bookingPerformance) {
        return bookingPerformanceRepository.save(bookingPerformance);
    }

    public BookingPerformance update(BookingPerformance bookingPerformance) {
        return bookingPerformanceRepository.save(bookingPerformance);
    }

    public List<BookingPerformance> findAll() {
        return bookingPerformanceRepository.findAll();
    }

    public List<BookingPerformance> findByBookingId(Long bookingId) {
        return bookingPerformanceRepository.findByBookingId(bookingId);
    }

    public List<BookingPerformance> findByPerformanceId(Long performanceId) {
        return bookingPerformanceRepository.findByPerformanceId(performanceId);
    }

    public BookingPerformance findById(Long id) {
        return bookingPerformanceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity BookingPerformance with id: " + id + "not found"));
    }

    public void delete(Long id) {
        bookingPerformanceRepository.deleteById(id);
    }

}
