package org.csu.bookstore.controller;

import org.csu.bookstore.domain.*;
import org.csu.bookstore.service.BookReviewService;
import org.csu.bookstore.service.CartService;
import org.csu.bookstore.service.CatalogService;
import org.csu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class reviewController {

    @Autowired
    private BookReviewService bookReviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CartService cartService;

    private User user = new User();


    @ModelAttribute("user")
    public User user() {
        return user;
    }

    @ModelAttribute("itemList")
    public List<Item> itemList(){
        return catalogService.getItem();
    }

    @ModelAttribute("cart")
    public  List<CartItem> cartItems(HttpSession session){
        User user=(User)session.getAttribute("user");
        List<CartItem> cartItems=new ArrayList<CartItem>();
        try {
            cartItems=cartService.getCartItem(user.getUserId());
            Book book ;
            for (int i=0;i<cartItems.size();i++){
                book=catalogService.getBook(cartItems.get(i).getBookId());
                cartItems.get(i).setBook(book);
                // catalogService.getBook(cartItems.get(i).getBookId());
            }
        }
        catch (Exception e){

        }
        return cartItems;
    }


    @GetMapping("/review-listing")
    public String viewReviewList(@RequestParam("currentPage" ) int currentPage,Model model) {

        List<BookReview> bookReviewList = bookReviewService.queryReviewsByArray(currentPage,3);
        List<BookReview> allItems = bookReviewService.getBookReviews();
        int pageNum;
        if(allItems.size()%3!=0){
           pageNum = (allItems.size()/3)+1;
        }else {
            pageNum = (allItems.size()/3);
        }
        int[] pagenum = new int[pageNum];
        for(int i=0 ; i<pageNum ; i++){
            pagenum[i]=i+1;
        }
        for (int i = 0; i < bookReviewList.size(); i++) {
            bookReviewList.get(i).setUser(userService.getUser(bookReviewList.get(i).getUserId()));
        }

        int countnum = allItems.size();

        model.addAttribute("countnum" , countnum);
        model.addAttribute("pageNum" , pageNum);
        model.addAttribute("pagenum" , pagenum);
        model.addAttribute("bookReviewList" , bookReviewList);
        model.addAttribute("currentPage" , currentPage);


        return "review/review-listing";
    }

    @GetMapping("/review-detail")
    public String viewReview(@RequestParam("reviewId") int reviewId, Model model) {
        BookReview bookReview = bookReviewService.selectByPrimaryKey(reviewId);
        bookReview.setUser(userService.getUser(bookReview.getUserId()));
        bookReview.setBook(catalogService.getBook(bookReview.getBookId()));
        Book book = catalogService.getBook(bookReview.getBookId());
        Image image = book.getImageList().get(0);
        model.addAttribute("bookreview", bookReview);
        model.addAttribute("image",image);
        return "review/review-detail";
    }

    @RequestMapping(value = "/review-search", method = RequestMethod.POST)
    public String viewSearch(@RequestParam(value = "keyword",required = false) String keyword, @RequestParam(value = "bookId",required = false) String bookId,Model model) {
        if (keyword != null) {
            List<BookReview> bookReviewList = bookReviewService.SearchBookReviewByText(keyword);
            for (int i = 0; i < bookReviewList.size(); i++) {
                bookReviewList.get(i).setUser(userService.getUser(bookReviewList.get(i).getUserId()));
            }
            model.addAttribute("bookReviewList", bookReviewList);
        }
        if(bookId!=null){
            int bookid=Integer.parseInt(bookId);
            List<BookReview> bookReviewList = bookReviewService.SearchBookReviewById(bookid);
            for (int i = 0; i < bookReviewList.size(); i++) {
                bookReviewList.get(i).setUser(userService.getUser(bookReviewList.get(i).getUserId()));
            }
            model.addAttribute("bookReviewList", bookReviewList);
        }
        return "review/review-listing";
    }

    @GetMapping(value = "/review-search")
    public String viewSearch( @RequestParam(value = "bookId",required = false) String bookId,Model model) {
        if(bookId!=null){
            int bookid=Integer.parseInt(bookId);
            List<BookReview> bookReviewList = bookReviewService.SearchBookReviewById(bookid);
            for (int i = 0; i < bookReviewList.size(); i++) {
                bookReviewList.get(i).setUser(userService.getUser(bookReviewList.get(i).getUserId()));
            }
            model.addAttribute("bookReviewList", bookReviewList);
        }
        return "review/review-listing";
    }

    @RequestMapping(value = "/add-review", method = RequestMethod.POST)
    public String  addReview(@RequestParam("title") String title, @RequestParam("text") String text, @RequestParam("bookId") String BookId,Model model, HttpSession session) {
        BookReview bookReview= new BookReview();
        Date datetime=new Date();
      //  String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        Timestamp date = new Timestamp(datetime.getTime());
        int bookid=Integer.parseInt(BookId);
        User user = (User) session.getAttribute("user");
        bookReview.setTitle(title);
        bookReview.setText(text);
        bookReview.setBookId(bookid);
        bookReview.setUserId(user.getUserId());
        bookReview.setDate(date);
        bookReviewService.insertBookReview(bookReview);
        ShowMsg("添加成功！");
        return "index";

    }
    public static String ShowMsg(String strMsg)
    {
        String str = "";
        str = "<script>alert('" + strMsg + "')</script>";
        return str;
    }
}


