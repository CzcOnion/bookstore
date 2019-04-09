package org.csu.bookstore.domain;

import java.util.Date;

public class Collection extends CollectionKey {
    private Date date;

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}