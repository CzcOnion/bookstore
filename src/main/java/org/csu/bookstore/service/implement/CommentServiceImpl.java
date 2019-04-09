package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.Comment;
import org.csu.bookstore.persistence.CommentMapper;
import org.csu.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insertComment(Comment comment) {
        try {
            comment.setCommentId(commentMapper.getNextId());
        }
        catch (Exception e){
            int userId = 1000000;
            comment.setCommentId(userId);
        }
        commentMapper.insert(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
    }
}
