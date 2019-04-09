package org.csu.bookstore.domain;

public class Admire {
    private Integer admireId;

    private Integer userId;

    private Integer reviewId;

    private Integer date;

    public Integer getAdmireId() {
        return admireId;
    }

    public void setAdmireId(Integer admireId) {
        this.admireId = admireId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
}