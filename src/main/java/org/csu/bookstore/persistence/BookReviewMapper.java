package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.BookReview;

import java.util.List;

public interface BookReviewMapper {
    int deleteByPrimaryKey(Integer reviewId);

    int insert(BookReview record);

    int insertSelective(BookReview record);

    BookReview selectByPrimaryKey(Integer reviewId);

    int updateByPrimaryKeySelective(BookReview record);

    int updateByPrimaryKeyWithBLOBs(BookReview record);

    int updateByPrimaryKey(BookReview record);

    int getNextId();

    List<BookReview> selectByBookId(Integer bookId);

    List<BookReview> getBookReviews();

    List<BookReview> SearchBookReviewByText(String text);

    List<BookReview> SearchBookReviewById(int bookId);

    List<BookReview> selectByUserId(Integer userId);
}