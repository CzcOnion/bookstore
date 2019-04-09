package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.Message;
import org.csu.bookstore.domain.MessageReply;
import org.csu.bookstore.persistence.*;
import org.csu.bookstore.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageReplyMapper messageReplyMapper;

    @Override
    public void insertMessage(Message message) {
        try {
            message.setMessageId(messageMapper.getNextId());
        }
        catch (Exception e){
            int userId = 1000000;
            message.setMessageId(messageMapper.getNextId());
        }
        messageMapper.insert(message);
    }

    @Override
    public void insertMessageReply(MessageReply messageReply) {
        try {
            messageReply.setMessageId(messageReplyMapper.getNextId());
        }
        catch (Exception e){
            int userId = 1000000;
            messageReply.setMessageId(messageReplyMapper.getNextId());
        }
        messageReplyMapper.insert(messageReply);
    }

    @Override
    public void deleteMessage(int messageId) {
        messageReplyMapper.deleteByPrimaryKey(messageId);
        messageMapper.deleteByPrimaryKey(messageId);
    }

    @Override
    public void deleteMessageReply(int messageRelyId) {
        messageReplyMapper.deleteByMessageId(messageRelyId);
    }
}
