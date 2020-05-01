package com.kmerconsulting.clariss.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ManagerSalon extends BasisEntity {
    @Column(nullable = false)
    Long salonId;
    @Column(nullable = false)
    Long userId;
    @Column(length = 300)
    String comment;

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long performanceId) {
        this.salonId = performanceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
