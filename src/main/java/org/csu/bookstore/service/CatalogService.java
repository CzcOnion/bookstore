package org.csu.bookstore.service;

import org.csu.bookstore.domain.Book;
import org.csu.bookstore.domain.BookReview;
import org.csu.bookstore.domain.Item;

import java.util.List;


public interface CatalogService {

    Book getBook(int bookId);


    List<Book> getBookList(int itemId);

    List<Book> getBookList(String text);

    List<Book> getBookList();

    BookReview getReview(int reviewId);

    List<BookReview> getBookReviewList(int bookId);

    List<BookReview> getBookReviewList();

    List<Book> queryStudentsByArray(int itemId , int currentPage, int pageSize);

    List<BookReview> getBookReviewListByUserId(int userId);

    Book getBookByBookName (String name);

    List<Item> getItem();

    List<Book> queryAllItemsByArray(int currentPage, int pageSize);
}
