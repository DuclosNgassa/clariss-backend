package com.kmerconsulting.clariss.controller;

import com.kmerconsulting.clariss.model.BookingPerformance;
import com.kmerconsulting.clariss.service.BookingPerformanceService;
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
@RequestMapping("/api/bookingPerformance")
public class BookingPerformanceController {
    @Autowired
    BookingPerformanceService bookingPerformanceService;

    @PostMapping()
    public ResponseEntity<BookingPerformance> save(@Valid @RequestBody BookingPerformance bookingPerformance) throws Exception {
        BookingPerformance createdBookingPerformance = bookingPerformanceService.save(bookingPerformance);
        if (createdBookingPerformance == null) {
            throw new Exception("Error while saving bookingPerformance");
        }
        return ResponseEntity.ok(createdBookingPerformance);
    }

    /**
     * This method updates a bookingPerformance based on his id and his details
     *
     * @param bookingPerformance
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookingPerformance> update(@PathVariable(value = "id") Long id, @Valid @RequestBody BookingPerformance bookingPerformance) throws Exception {
        BookingPerformance bookingPerformanceFound = bookingPerformanceService.findById(id);
        if (bookingPerformanceFound == null) {
            throw new Exception("Error while saving bookingPerformance");
        }

        bookingPerformanceFound.setBookingId(bookingPerformance.getBookingId());
        bookingPerformanceFound.setPerformanceId(bookingPerformance.getPerformanceId());

        BookingPerformance updatedBookingPerformance = bookingPerformanceService.update(bookingPerformanceFound);


        return ResponseEntity.ok(updatedBookingPerformance);
    }

    /**
     * get all bookingPerformancees
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<BookingPerformance>> findAll() {
        List<BookingPerformance> bookingPerformancees = bookingPerformanceService.findAll();
        if (bookingPerformancees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookingPerformancees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingPerformance> findById(@PathVariable(value = "id") Long id) {
        BookingPerformance bookingPerformance = bookingPerformanceService.findById(id);
        if (bookingPerformance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookingPerformance);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<BookingPerformance>> findByBookingId(@PathVariable(value = "bookingId") Long bookingId) {
        List<BookingPerformance> bookingPerformancees = bookingPerformanceService.findByBookingId(bookingId);
        if (bookingPerformancees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookingPerformancees);
    }

    @GetMapping("/performance/{performanceId}")
    public ResponseEntity<List<BookingPerformance>> findByPerformanceId(@PathVariable(value = "performanceId") Long performanceId) {
        List<BookingPerformance> bookingPerformancees = bookingPerformanceService.findByPerformanceId(performanceId);
        if (bookingPerformancees == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookingPerformancees);
    }

    /**
     * This method deletes a bookingPerformance based on his id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<BookingPerformance> delete(@PathVariable(value = "id") Long id) throws Exception {
        BookingPerformance bookingPerformance = bookingPerformanceService.findById(id);
        if (bookingPerformance == null) {
            throw new Exception("Error while saving bookingPerformance");
        }

        bookingPerformanceService.delete(id);

        return ResponseEntity.ok(bookingPerformance);
    }

}
