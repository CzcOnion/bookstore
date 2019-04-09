package org.csu.bookstore.domain;

import java.util.Date;

public class MessageReply {
    private Integer mreplyId;

    private Integer messageId;

    private Integer userId;

    private Date date;

    private String text;

    public Integer getMreplyId() {
        return mreplyId;
    }

    public void setMreplyId(Integer mreplyId) {
        this.mreplyId = mreplyId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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