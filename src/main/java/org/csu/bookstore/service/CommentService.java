package org.csu.bookstore.service;

import org.csu.bookstore.domain.Comment;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {

    @Transactional
    void insertComment(Comment comment);

    @Transactional
    void deleteComment(int commentId);
}
