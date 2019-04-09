package org.csu.bookstore.domain;

public class CartItem extends CartItemKey {
    private Integer count;
    private Book book;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}