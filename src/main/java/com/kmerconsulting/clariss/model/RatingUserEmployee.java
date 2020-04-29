package com.kmerconsulting.clariss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class RatingUserEmployee extends BasisEntity {
    @Column(nullable = false)
    Integer score;
    @Column(nullable = false)
    Long employeeId;
    @Column(nullable = false)
    Long userId;
    @Column(length = 300)
    String comment;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RatingAuthor author;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long performanceId) {
        this.employeeId = performanceId;
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

    public RatingAuthor getAuthor() {
        return author;
    }

    public void setAuthor(RatingAuthor author) {
        this.author = author;
    }
}
