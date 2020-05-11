package com.kmerconsulting.clariss.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Booking extends BasisEntity {
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime performanceDate;
    @Column(nullable = false, length = 300)
    private String note;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long salonId;
    @Column(nullable = false)
    private Long addressId;

    public LocalDateTime getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(LocalDateTime performanceDate) {
        this.performanceDate = performanceDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
