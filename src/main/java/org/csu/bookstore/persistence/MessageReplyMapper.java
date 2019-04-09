package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.MessageReply;

import java.util.List;

public interface MessageReplyMapper {
    int deleteByPrimaryKey(Integer mreplyId);

    int insert(MessageReply record);

    int insertSelective(MessageReply record);

    MessageReply selectByPrimaryKey(Integer mreplyId);

    int updateByPrimaryKeySelective(MessageReply record);

    int updateByPrimaryKey(MessageReply record);

    int getNextId();

    List<MessageReply> selectByMessageId(Integer messageId);

    void deleteByMessageId(Integer messageId);
}