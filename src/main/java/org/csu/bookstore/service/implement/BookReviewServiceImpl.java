package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.*;
import org.csu.bookstore.persistence.*;
import org.csu.bookstore.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewServiceImpl implements BookReviewService {

    @Autowired
    private BookReviewMapper bookReviewMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageReplyMapper messageReplyMapper;

    public void insertBookReview(BookReview bookReview) {
        try {
            bookReview.setReviewId(bookReviewMapper.getNextId());
        }
        catch (Exception e){
            int reviewId = 1000000;
            bookReview.setReviewId(reviewId);
        }
        bookReviewMapper.insert(bookReview);
    }

    @Override
    public void updateBookReview(BookReview bookReview) {
        bookReviewMapper.updateByPrimaryKey(bookReview);
    }

    @Override
    public void deleteBookReview(int reviewId) {
        BookReview bookReview = bookReviewMapper.selectByPrimaryKey(reviewId);
        for(int i=0;i<bookReview.getMessageList().size();i++) {
            Message message = bookReview.getMessageList().get(i);
            messageMapper.deleteByPrimaryKey(message.getMessageId());
            for (int k = 0; k<message.getMessageReplyList().size();k++)
            {
                messageReplyMapper.deleteByPrimaryKey(message.getMessageReplyList().get(k).getMreplyId());
            }
        }
        bookReviewMapper.deleteByPrimaryKey(reviewId);
    }

    @Override
    public List<BookReview> getBookReviews(){
        return bookReviewMapper.getBookReviews();
    }

    @Override
    public BookReview selectByPrimaryKey(Integer reviewId){
        return bookReviewMapper.selectByPrimaryKey(reviewId);
    }

    @Override
    public List<BookReview> SearchBookReviewByText(String text){
        return bookReviewMapper.SearchBookReviewByText(text);
    }

    @Override
    public List<BookReview> SearchBookReviewById(int bookId){
        return bookReviewMapper.SearchBookReviewById(bookId);
    }

    @Override
    public List<BookReview> queryReviewsByArray(int currentPage, int pageSize){
        List<BookReview> allItems = bookReviewMapper.getBookReviews();     //全部商品
        int countNums = allItems.size();            //总记录数
        int firstIndex = (currentPage - 1) * pageSize;
//        到第几条数据结束
        int lastIndex = currentPage * pageSize;
        if(lastIndex>=countNums){
            if(lastIndex==countNums){
                firstIndex=(countNums/pageSize-1)*pageSize;
            }else{
                firstIndex=(countNums/pageSize)*pageSize;
            }
            lastIndex=countNums;
        }

        return allItems.subList(firstIndex, lastIndex);
    }


}
