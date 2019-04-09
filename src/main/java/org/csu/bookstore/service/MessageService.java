package org.csu.bookstore.service;

import org.csu.bookstore.domain.Message;
import org.csu.bookstore.domain.MessageReply;
import org.springframework.transaction.annotation.Transactional;

public interface MessageService {

    @Transactional
    void deleteMessage(int messageId);

    @Transactional
    void deleteMessageReply(int messageRely);

    @Transactional
    void insertMessage(Message message);

    @Transactional
    void insertMessageReply(MessageReply messageReply);
}
