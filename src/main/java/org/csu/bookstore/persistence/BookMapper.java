package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Book;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    int getNextId();

    List<Book> selectByItemId(Integer itemId);

    List<Book> getBooks();

    List<Book> searchBook(String text);

    Book selectByBookName(String bookName);
}