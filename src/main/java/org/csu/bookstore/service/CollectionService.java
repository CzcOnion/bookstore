package org.csu.bookstore.service;

import org.csu.bookstore.domain.Collection;

import java.util.List;

public interface CollectionService {

    List<Collection> getCollectionList(int userId);

    void insertCollection(Collection collection);
}
