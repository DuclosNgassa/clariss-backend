package com.kmerconsulting.clariss.service;

import com.kmerconsulting.clariss.model.Booking;
import com.kmerconsulting.clariss.model.BookingPerformance;
import com.kmerconsulting.clariss.model.Performance;
import com.kmerconsulting.clariss.repository.BookingRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookingPerformanceService bookingPerformanceService;
    @Autowired
    PerformanceService performanceService;

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findBySalonId(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    public List<Booking> findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Booking with id: " + id + "not found"));
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

}
