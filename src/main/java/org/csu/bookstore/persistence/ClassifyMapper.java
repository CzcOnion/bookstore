package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.ClassifyKey;

import java.util.List;

public interface ClassifyMapper {
    int deleteByPrimaryKey(ClassifyKey key);

    int insert(ClassifyKey record);

    int insertSelective(ClassifyKey record);

    List<ClassifyKey> getClassifyKeyByItemId(int itemId);

    List<ClassifyKey> getClassifyKeyByBookName(String bookName);

    List<ClassifyKey> selectByBookName(String bookId);

    List<ClassifyKey> selectByItemId(Integer itemId);
}