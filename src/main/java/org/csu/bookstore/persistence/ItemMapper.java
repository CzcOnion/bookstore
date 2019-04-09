package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Item;

import java.util.List;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    int getNextId();

    List<Item> getItem();
}