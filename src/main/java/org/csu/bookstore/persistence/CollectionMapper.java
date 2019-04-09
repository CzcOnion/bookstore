package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Collection;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer collectionId);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer collectionId);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    int getNextId();

    List<Collection> selectByUserId(Integer userId);
}