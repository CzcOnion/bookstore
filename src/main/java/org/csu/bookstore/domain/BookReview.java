package org.csu.bookstore.domain;

import java.sql.Timestamp;
import java.util.List;

public class BookReview {
    private Integer reviewId;

    private Integer bookId;

    private Integer userId;

    private Timestamp  date;

    private Integer count;

    private String title;

    private String text;

    private List<Message> messageList;

    private User user;

    private Book book;

    public User getUser() { return user; }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp  getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }



    public void setUser(User user) { this.user = user; }

    public Book getBook(){ return book; }

    public void setBook(Book book){ this.book = book; }
}