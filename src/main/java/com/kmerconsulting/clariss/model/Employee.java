package com.kmerconsulting.clariss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Employee extends BasisEntity {
    @Column(nullable = false)
    Long salonId;
    @Column(precision = 2, scale = 1)
    double rating;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String position;
    @Column(length = 300)
    private String description;
    @Column(length = 255)
    private String picture;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public GlobalStatus getStatus() {
        return status;
    }

    public void setStatus(GlobalStatus status) {
        this.status = status;
    }
}
