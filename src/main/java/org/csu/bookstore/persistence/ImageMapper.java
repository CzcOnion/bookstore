package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Image;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    int getNextId();

    List<Image> selectByBookName(String bookName);
}