package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.Booking;
import com.kmerconsulting.clariss.service.BookingService;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping()
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking) throws Exception {
        booking.setCreatedAt(LocalDateTime.now());
        Booking createdBooking = bookingService.save(booking);

        if (createdBooking == null) {
            throw new Exception("Error while saving booking");
        }

        return ResponseEntity.ok(createdBooking);
    }

    @GetMapping()
    public ResponseEntity<List<Booking>> findAll() {
        List<Booking> bookings = bookingService.findAll();

        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable(value = "id") Long id) {
        Booking booking = bookingService.findById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Booking>> findBySalonId(@PathVariable(value = "salonId") Long salonId) {
        List<Booking> bookings = bookingService.findBySalonId(salonId);
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> findByUserId(@PathVariable(value = "userId") Long userId) {
        List<Booking> bookings = bookingService.findByUserId(userId);
        if (bookings == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookings);
    }

    /**
     * This method updates a booking based on his id and his details
     *
     * @param bookingDetail
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Booking bookingDetail) {
        Booking booking = bookingService.findById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        booking.setComment(bookingDetail.getComment());
        booking.setAddressId(bookingDetail.getAddressId());
        booking.setPerformanceDate(bookingDetail.getPerformanceDate());
        booking.setUserId(bookingDetail.getUserId());

        Booking updatedBooking = bookingService.update(booking);

        return ResponseEntity.ok(updatedBooking);
    }

    /**
     * This method deletes a booking based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> delete(@PathVariable(value = "id") Long id) {
        Booking booking = bookingService.findById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        bookingService.delete(id);

        return ResponseEntity.ok(booking);
    }
}
