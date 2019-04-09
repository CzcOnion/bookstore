package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Admire;

public interface AdmireMapper {
    int deleteByPrimaryKey(Integer admireId);

    int insert(Admire record);

    int insertSelective(Admire record);

    Admire selectByPrimaryKey(Integer admireId);

    int updateByPrimaryKeySelective(Admire record);

    int updateByPrimaryKey(Admire record);

    int getNextId();

    int getCountByReviewId(Integer reviewId);
}