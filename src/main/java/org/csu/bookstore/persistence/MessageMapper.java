package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    int getNextId();

    List<Message> selectByReviewId(Integer reviewId);
}