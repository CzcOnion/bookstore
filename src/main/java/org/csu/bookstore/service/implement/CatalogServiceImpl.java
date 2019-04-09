package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.*;
import org.csu.bookstore.persistence.*;
import org.csu.bookstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private BookReviewMapper bookReviewMapper;

    @Autowired
    private AdmireMapper admireMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private MessageReplyMapper messageReplyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Book getBook(int bookId) {
        Book book = bookMapper.selectByPrimaryKey(bookId);
        List<Image> imageList = imageMapper.selectByBookName(book.getName());
        List<BookReview> bookReviewList = bookReviewMapper.selectByBookId(bookId);
        List<Comment> commentList = commentMapper.selectByBookId(bookId);
        List<ClassifyKey> classifyList = classifyMapper.getClassifyKeyByBookName(book.getName());
        book.setClassifyList(classifyList);
        book.setImageList(imageList);
        book.setBookReviewList(bookReviewList);
        book.setCommentList(commentList);
        return book;
    }

    @Override
    public List<Book> getBookList(int itemId) {
        List<Book> bookList = bookMapper.selectByItemId(itemId);
        for (int i = 0; i < bookList.size(); i++) {
            List<Image> imageList = imageMapper.selectByBookName(bookList.get(i).getName());
            List<BookReview> bookReviewList = bookReviewMapper.selectByBookId(bookList.get(i).getBookId());
            List<Comment> commentList = commentMapper.selectByBookId(bookList.get(i).getBookId());
            List<ClassifyKey> classifyList = classifyMapper.getClassifyKeyByBookName(bookList.get(i).getName());
            bookList.get(i).setImageList(imageList);
            bookList.get(i).setBookReviewList(bookReviewList);
            bookList.get(i).setCommentList(commentList);
            bookList.get(i).setClassifyList(classifyList);
        }
        return bookList;
    }

    @Override
    public List<Book> getBookList() {
        List<Book> bookList = bookMapper.getBooks();
        for (int i = 0; i < bookList.size(); i++) {
            getBook(bookList.get(i).getBookId());
        }
        return bookList;
    }

    @Override
    public List<Book> getBookList(String text) {
        List<Book> bookList = bookMapper.searchBook(text);
        for (int i = 0; i < bookList.size(); i++) {
            List<Image> imageList = imageMapper.selectByBookName(bookList.get(i).getName());
            List<BookReview> bookReviewList = bookReviewMapper.selectByBookId(bookList.get(i).getBookId());
            List<Comment> commentList = commentMapper.selectByBookId(bookList.get(i).getBookId());
            List<ClassifyKey> classifyList = classifyMapper.getClassifyKeyByBookName(bookList.get(i).getName());
            bookList.get(i).setImageList(imageList);
            bookList.get(i).setBookReviewList(bookReviewList);
            bookList.get(i).setCommentList(commentList);
            bookList.get(i).setClassifyList(classifyList);
        }
        return bookList;
    }


    @Override
    public BookReview getReview(int reviewId) {
        BookReview bookReview = bookReviewMapper.selectByPrimaryKey(reviewId);
        //评论
        List<Message> messageList = messageMapper.selectByReviewId(reviewId);
        for (int i = 0; i < messageList.size(); i++) {
            //评论回复
            List<MessageReply> messageReplyList = messageReplyMapper.selectByMessageId(messageList.get(i).getMessageId());
            messageList.get(i).setMessageReplyList(messageReplyList);
        }
        //点赞数
        int count = admireMapper.getCountByReviewId(reviewId);
        bookReview.setUser(userMapper.selectByPrimaryKey(bookReview.getUserId()));
        bookReview.setCount(count);
        bookReview.setMessageList(messageList);
        return bookReview;
    }

    @Override
    public List<BookReview> getBookReviewList(int bookId){
        List<BookReview> bookReviewListTmp = bookReviewMapper.selectByUserId(bookId);
        List<BookReview> bookReviewList = new ArrayList<>();
        for(int i=0;i<bookReviewListTmp.size();i++)
        {
            bookReviewList.add(getReview(bookReviewListTmp.get(i).getReviewId()));
        }
        return bookReviewList;
    }

    @Override
    public List<BookReview> getBookReviewList(){
        List<BookReview> bookReviewListTmp = bookReviewMapper.getBookReviews();
        List<BookReview> bookReviewList = new ArrayList<>();
        for(int i=0;i<bookReviewListTmp.size();i++)
        {
            bookReviewList.add(getReview(bookReviewListTmp.get(i).getReviewId()));
        }
        return bookReviewList;
    }



    @Override
    public List<BookReview> getBookReviewListByUserId(int userId) {
        List<BookReview> bookReviewListTmp = bookReviewMapper.selectByUserId(userId);
        List<BookReview> bookReviewList = new ArrayList<>();
        for(int i=0;i<bookReviewListTmp.size();i++)
        {
            bookReviewList.add(getReview(bookReviewListTmp.get(i).getReviewId()));
        }
        return bookReviewList;
    }



    @Override
    public List<Book> queryStudentsByArray(int itemId , int currentPage, int pageSize){
        List<Book> allItems = bookMapper.selectByItemId(itemId);        //全部商品List<Book> book = bookMapper.getBookList();
        int countNums = allItems.size();            //总记录数
        for (int i = 0; i < countNums; i++) {
            List<Image> imageList = imageMapper.selectByBookName(allItems.get(i).getName());
            List<BookReview> bookReviewList = bookReviewMapper.selectByBookId(allItems.get(i).getBookId());
            List<Comment> commentList = commentMapper.selectByBookId(allItems.get(i).getBookId());
            List<ClassifyKey> classifyList = classifyMapper.getClassifyKeyByBookName(allItems.get(i).getName());
            allItems.get(i).setImageList(imageList);
            allItems.get(i).setBookReviewList(bookReviewList);
            allItems.get(i).setCommentList(commentList);
            allItems.get(i).setClassifyList(classifyList);
        }
        int firstIndex = (currentPage - 1) * pageSize;
//        到第几条数据结束
        int lastIndex = currentPage * pageSize;
        if(lastIndex>=countNums){
            firstIndex=(countNums/pageSize)*pageSize;
            lastIndex=countNums;
        }
        return allItems.subList(firstIndex, lastIndex);
    }

    @Override
    public Book getBookByBookName (String name){
        Book book = bookMapper.selectByBookName(name);
        List<Image> imageList = imageMapper.selectByBookName(book.getName());
        List<BookReview> bookReviewList = bookReviewMapper.selectByBookId(book.getBookId());
        List<Comment> commentList = commentMapper.selectByBookId(book.getBookId());
        List<ClassifyKey> classifyList = classifyMapper.getClassifyKeyByBookName(book.getName());
        book.setImageList(imageList);
        book.setBookReviewList(bookReviewList);
        book.setCommentList(commentList);
        book.setClassifyList(classifyList);
        return book;
    }

    @Override
    public List<Book> queryAllItemsByArray(int currentPage, int pageSize){
        List<Book> allItems = bookMapper.getBooks();        //全部商品List<Book> book = bookMapper.getBookList();
        int countNums = allItems.size();            //总记录数
        for (int i = 0; i < countNums; i++) {
            List<Image> imageList = imageMapper.selectByBookName(allItems.get(i).getName());
            List<BookReview> bookReviewList = bookReviewMapper.selectByBookId(allItems.get(i).getBookId());
            List<Comment> commentList = commentMapper.selectByBookId(allItems.get(i).getBookId());
            List<ClassifyKey> classifyList = classifyMapper.getClassifyKeyByBookName(allItems.get(i).getName());
            allItems.get(i).setImageList(imageList);
            allItems.get(i).setBookReviewList(bookReviewList);
            allItems.get(i).setCommentList(commentList);
            allItems.get(i).setClassifyList(classifyList);
        }
        int firstIndex = (currentPage - 1) * pageSize;
//        到第几条数据结束
        int lastIndex = currentPage * pageSize;
        if(lastIndex>=countNums){
            firstIndex=(countNums/pageSize)*pageSize;
            lastIndex=countNums;
        }
        return allItems.subList(firstIndex, lastIndex);
    }
    @Override
    public List<Item> getItem(){
        return itemMapper.getItem();
    }


}