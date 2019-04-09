package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Label;

public interface LabelMapper {
    int deleteByPrimaryKey(Integer labelId);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    int getNextId();
}