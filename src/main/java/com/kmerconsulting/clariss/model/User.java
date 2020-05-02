package com.kmerconsulting.clariss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class User extends BasisEntity {
    @Column(nullable = false)
    Long addressId;
    @Column(nullable = false)
    String deviceToken;
    @Column(nullable = false)
    String deviceId;
    @Column(precision = 2, scale = 1)
    //@Digits(integer = 2 /*precision*/, fraction = 1 /*scale*/)
            double rating;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(length = 300, nullable = false)
    private String password;
    @Column(length = 45, nullable = false)
    private String phone;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GlobalStatus status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public GlobalStatus getStatus() {
        return status;
    }

    public void setStatus(GlobalStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
