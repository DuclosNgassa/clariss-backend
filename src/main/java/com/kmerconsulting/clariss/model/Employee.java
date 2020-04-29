package com.kmerconsulting.clariss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String function;
    @Column(length = 300)
    private String description;
    @Column(length = 255)
    private String imageProfilUrl;
    @Column(nullable = false)
    Long salonId;
    @Column(precision = 2, scale = 1)
    double rating;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageProfilUrl() {
        return imageProfilUrl;
    }

    public void setImageProfilUrl(String imageProfilUrl) {
        this.imageProfilUrl = imageProfilUrl;
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
}
