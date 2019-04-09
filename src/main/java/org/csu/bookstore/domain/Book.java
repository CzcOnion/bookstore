package org.csu.bookstore.domain;

import java.util.List;

public class Book {
    private Integer bookId;

    private String name;

    private String description;

    private Float price;

    private Integer quantity;

    private Integer collectedCount;

    private Float score;

    private Integer sale;

    private String writer;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private List<Image> imageList;

    private List<Comment> commentList;

    private List<ClassifyKey>  classifyList;

    public List<ClassifyKey> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<ClassifyKey> classifyList) {
        this.classifyList = classifyList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<BookReview> getBookReviewList() {
        return bookReviewList;
    }

    public void setBookReviewList(List<BookReview> bookReviewList) {
        this.bookReviewList = bookReviewList;
    }

    private List<BookReview> bookReviewList;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCollectedCount() {
        return collectedCount;
    }

    public void setCollectedCount(Integer collectedCount) {
        this.collectedCount = collectedCount;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer == null ? null : writer.trim();
    }
}