package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.Book;
import org.csu.bookstore.domain.ClassifyKey;
import org.csu.bookstore.domain.Item;
import org.csu.bookstore.persistence.BookMapper;
import org.csu.bookstore.persistence.ClassifyMapper;
import org.csu.bookstore.persistence.ItemMapper;
import org.csu.bookstore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getBookListByItemId(int itemId) {
        List<ClassifyKey> classifyKeyList = classifyMapper.selectByItemId(itemId);
        List<Book> bookList = new ArrayList<>();
        for(int i = 0;i<classifyKeyList.size();i++){
            bookList.add(bookMapper.selectByBookName(classifyKeyList.get(i).getBookName()));
        }
        return bookList;
    }

    @Override
    public List<Item> getItemListByBookName(String bookName) {
        List<ClassifyKey> classifyKeyList = classifyMapper.selectByBookName(bookName);
        List<Item> itemList = new ArrayList<>();
        for(int i = 0;i<classifyKeyList.size();i++){
            itemList.add(itemMapper.selectByPrimaryKey(classifyKeyList.get(i).getItemId()));
        }
        return itemList;
    }
}
