package org.csu.bookstore.service;

import org.csu.bookstore.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookReviewService {

    @Transactional
    void insertBookReview(BookReview bookReview);

    @Transactional
    void updateBookReview(BookReview bookReview);

    @Transactional
    void deleteBookReview(int reviewId);

    List<BookReview> getBookReviews();

    BookReview selectByPrimaryKey(Integer reviewId);

    List<BookReview> SearchBookReviewByText(String text);

    List<BookReview> SearchBookReviewById(int bookId);

    List<BookReview> queryReviewsByArray(int currentPage, int pageSize);

}
