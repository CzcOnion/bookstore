package org.csu.bookstore.service.implement;


import org.csu.bookstore.domain.Collection;
import org.csu.bookstore.persistence.BookMapper;
import org.csu.bookstore.persistence.ClassifyMapper;
import org.csu.bookstore.persistence.CollectionMapper;
import org.csu.bookstore.persistence.ItemMapper;
import org.csu.bookstore.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ClassifyMapper classifyMapper;

    @Override
    public List<Collection> getCollectionList(int userId) {
        List<Collection> collectionList =collectionMapper.selectByUserId(userId);
        for(int i = 0;i<collectionList.size();i++){
            collectionList.get(i).setBook(bookMapper.selectByPrimaryKey(collectionList.get(i).getBookId()));
        }
        return collectionList;
    }

    @Override
    public void insertCollection(Collection collection) {
        collectionMapper.insert(collection);
    }
}
