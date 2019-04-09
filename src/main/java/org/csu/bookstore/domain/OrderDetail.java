package org.csu.bookstore.domain;

public class OrderDetail extends OrderDetailKey {
    private Integer count;

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}