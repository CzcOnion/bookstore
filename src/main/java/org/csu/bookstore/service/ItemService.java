package org.csu.bookstore.service;

import org.csu.bookstore.domain.Book;
import org.csu.bookstore.domain.Item;

import java.util.List;

public interface ItemService {
    List<Book> getBookListByItemId(int itemId);

    List<Item> getItemListByBookName(String bookName);
}
