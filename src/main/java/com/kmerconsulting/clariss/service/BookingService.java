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
        List<Performance> salonPerformances = performanceService.findBySalonId(salonId);
        Set<Long> bookingIds = new HashSet<>();
        salonPerformances.forEach((performance -> {
            List<BookingPerformance> bookingPerformances = bookingPerformanceService.findByPerformanceId(performance.getId());
            bookingPerformances.stream().map((item) -> bookingIds.add(item.getBookingId()));
        }));

        List<Booking> bookings = bookingIds.stream()
                .map(bookingRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return bookings;
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
