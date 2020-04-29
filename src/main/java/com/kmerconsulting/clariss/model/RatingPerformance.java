package com.kmerconsulting.clariss.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RatingPerformance extends BasisEntity{
    @Column(nullable = false)
    Integer score;
    @Column(nullable = false)
    Long performanceId;
    @Column(nullable = false)
    Long userId;
    @Column(length = 300)
    String comment;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
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
