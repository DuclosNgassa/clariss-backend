package com.kmerconsulting.clariss.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Booking extends BasisEntity{
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime performanceDate;
    @Column(nullable = false, length = 300)
    private String comment;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long addressId;

    public LocalDateTime getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(LocalDateTime performanceDate) {
        this.performanceDate = performanceDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
