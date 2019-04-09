package org.csu.bookstore.domain;

import java.util.Date;
import java.util.List;

public class Message {
    private Integer messageId;

    private Integer reviewId;

    private Integer userId;

    private Date date;

    private String text;

    private List<MessageReply> messageReplyList;

    public List<MessageReply> getMessageReplyList() {
        return messageReplyList;
    }

    public void setMessageReplyList(List<MessageReply> messageReplyList) {
        this.messageReplyList = messageReplyList;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}