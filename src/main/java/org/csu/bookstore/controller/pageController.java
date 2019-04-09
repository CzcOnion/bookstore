package org.csu.bookstore.controller;

import org.csu.bookstore.domain.*;
import org.csu.bookstore.persistence.BookMapper;
import org.csu.bookstore.persistence.BookReviewMapper;
import org.csu.bookstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class pageController {

    @Autowired
    private UserService userService;

    private User user = new User();

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookReviewMapper bookReviewMapper;

    @ModelAttribute("user")
    public User user(){
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

    @GetMapping("/")
    public String viewIndex(Model model){
        List<Item> itemList = catalogService.getItem();
        List<Book> literatureBookList = catalogService.getBookList(1000002);
        List<Book> PopularBookList = catalogService.getBookList(1000031);
        List<Book> CultureBookList = catalogService.getBookList(1000066);
        List<Book> LifeBookList = catalogService.getBookList(1000096);
        model.addAttribute("literatureBookList",literatureBookList);
        model.addAttribute("PopularBookList",PopularBookList);
        model.addAttribute("CultureBookList",CultureBookList);
        model.addAttribute("LifeBookList",LifeBookList);
        model.addAttribute("itemList",itemList);
        return "index";
    }

    @GetMapping("/product-listing")
    public String showProductList(HttpServletRequest request, @RequestParam(name = "itemId" ,required=true) int itemId, Model model, HttpSession session){
        if(itemId != 0){
//            Item item = catalogService.getItem(itemId);
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            session.setAttribute(String.valueOf(currentPage),currentPage);
            List<Book> bookList = catalogService.queryStudentsByArray(itemId,currentPage, 8);
            List<Book> allItems = bookMapper.selectByItemId(itemId);
            int pageNum = (allItems.size()/8)+1;
            int[] pagenum = new int[pageNum];
            for(int i=0 ; i<pageNum ; i++){
                pagenum[i]=i+1;
            }

            int[] imageNum = new int[8];//记录每本书对应的图片个数

            for(int i=0 ; i<bookList.size() ; i++){
                List<Image> ImageList = bookList.get(i).getImageList();
                imageNum[i] = ImageList.size();

            }
            int countnum = allItems.size();
            int imageCount = 0;
            List<Item> itemList = catalogService.getItem();
            model.addAttribute("itemList",itemList);
            model.addAttribute("itemId" , itemId);
            model.addAttribute("countnum" , countnum);
            model.addAttribute("pageNum" , pageNum);
            model.addAttribute("pagenum" , pagenum);
            model.addAttribute("imageNum" , imageNum);
            model.addAttribute("imageCount" , imageCount);
            model.addAttribute("bookList" , bookList);
            model.addAttribute("currentPage" , currentPage);
            model.addAttribute("user",user);

        }
        else{
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            session.setAttribute(String.valueOf(currentPage),currentPage);
            List<Book> bookList = catalogService.queryAllItemsByArray(currentPage, 8);
            List<Book> allItems = bookMapper.getBooks();
            int pageNum = (allItems.size()/8)+1;
            int[] pagenum = new int[pageNum];
            for(int i=0 ; i<pageNum ; i++){
                pagenum[i]=i+1;
            }
            int[] imageNum = new int[8];//记录每本书对应的图片个数
            for(int i=0 ; i<bookList.size() ; i++){
                List<Image> ImageList = bookList.get(i).getImageList();
                imageNum[i] = ImageList.size();
            }
            int countnum = allItems.size();
            int imageCount = 0;
            List<Item> itemList = catalogService.getItem();
            model.addAttribute("itemList",itemList);
            model.addAttribute("itemId" , itemId);
            model.addAttribute("countnum" , countnum);
            model.addAttribute("pageNum" , pageNum);
            model.addAttribute("pagenum" , pagenum);
            model.addAttribute("imageNum" , imageNum);
            model.addAttribute("imageCount" , imageCount);
            model.addAttribute("bookList" , bookList);
            model.addAttribute("currentPage" , currentPage);
            model.addAttribute("user",user);
        }

        return "/product/product-listing";
    }

    @GetMapping("/product-detail")
    public String viewProduct(@RequestParam(name = "bookName" ,required = false) String name,@RequestParam(name = "bookId",required = false) String  bookId,Model model, HttpSession session,HttpServletRequest request){

        if(name != null){
            Book book = catalogService.getBookByBookName(name);
            List<ClassifyKey> classifyList  =book.getClassifyList();
            int itemId = classifyList.get(0).getItemId();
            List<Book> bookList = catalogService.queryStudentsByArray(itemId,1, 8);
            List<Item> itemList = catalogService.getItem();
            model.addAttribute("itemList",itemList);
            model.addAttribute("name" , name);
            model.addAttribute("bookList" , bookList);
            model.addAttribute("book" , book);
            model.addAttribute("itemId",itemId);
            model.addAttribute("user",user);
        }
        if(bookId!=null){
            Book book = catalogService.getBook(Integer.parseInt(bookId));
            List<ClassifyKey> classifyList  =book.getClassifyList();
            int itemId = classifyList.get(0).getItemId();
            List<Book> bookList = catalogService.queryStudentsByArray(itemId,1, 8);
            List<Item> itemList = catalogService.getItem();
            model.addAttribute("itemList",itemList);
            model.addAttribute("name" , book.getName());
            model.addAttribute("bookList" , bookList);
            model.addAttribute("book" , book);
            model.addAttribute("itemId",itemId);
            model.addAttribute("user",user);
        }
        return "product/product-detail";
    }



    @RequestMapping(value = "/searchBook",method = RequestMethod.POST)
    public String searchBook(@RequestParam("text")String text,HttpSession session,HttpServletRequest request,Model model){
//            Item item = catalogService.getItem(itemId);
           // int currentPage = Integer.parseInt(request.getParameter("currentPage"));
           int currentPage=1;
            session.setAttribute(String.valueOf(currentPage),currentPage);
            List<Book> bookList = catalogService.getBookList(text);
            List<Book> allItems = bookMapper.searchBook(text);
            int pageNum = (allItems.size()/8)+1;
            int[] pagenum = new int[pageNum];
            for(int i=0 ; i<pageNum ; i++){
                pagenum[i]=i+1;
            }

            int[] imageNum = new int[10000];//记录每本书对应的图片个数

            for(int i=0 ; i<bookList.size() ; i++){
                List<Image> ImageList = bookList.get(i).getImageList();
                imageNum[i] = ImageList.size();
            }
            int countnum = allItems.size();
            int imageCount = 0;
            List<Item> itemList = catalogService.getItem();
            model.addAttribute("itemList",itemList);
            model.addAttribute("countnum" , countnum);
            model.addAttribute("pageNum" , pageNum);
            model.addAttribute("pagenum" , pagenum);
            model.addAttribute("imageNum" , imageNum);
            model.addAttribute("imageCount" , imageCount);
            model.addAttribute("bookList" , bookList);
            model.addAttribute("currentPage" , currentPage);
            model.addAttribute("user",user);
           model.addAttribute("itemId" , 1000001);

        return "/product/product-listing";

    }

    @GetMapping("/personal")
    public String viewPersonal(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Order> orderList = orderService.getOrderList(user.getUserId());
        List<Collection> collectionList = collectionService.getCollectionList(user.getUserId());
        List<Item> itemList = new ArrayList<>();

        String itemName="";
        for(int i=0;i<collectionList.size();i++)
        {
            Item item = itemService.getItemListByBookName(collectionList.get(i).getBook().getName()).get(0);
            if (i == 0) {
                itemName += item.getName();
                itemList.add(item);
            }
            else {
                if(itemName.contains(item.getName())) {
                    continue;
                }
                else{
                    itemName = itemName +" "+ item.getName();
                    itemList.add(item);
                }
            }
        }


        for(int i = 0;i<itemList.size();i++)
        {
            List<Book> bookList = itemService.getBookListByItemId(itemList.get(i).getItemId());
            List<Book> bookListTmp = new ArrayList<>();
            for(int k = 0;k<bookList.size();k++){
                for(int j =0;j<collectionList.size();j++)
                {
                    if(collectionList.get(j).getBookId().equals(bookList.get(k).getBookId())){

                        bookListTmp.add(catalogService.getBook(bookList.get(k).getBookId()));
                    }
                    else
                        continue;
                }
            }
            itemList.get(i).setBookList(bookListTmp);
        }

        List<BookReview> bookReviewList = catalogService.getBookReviewListByUserId(user.getUserId());
        model.addAttribute("bookReviewList",bookReviewList);
        model.addAttribute("itemName",itemName);
        model.addAttribute("itemList",itemList);
        model.addAttribute("orderList",orderList);
        model.addAttribute("collectionList",collectionList);
        model.addAttribute("user",user);
        return "account/personal";
    }

    @ResponseBody
    @RequestMapping(value = "addScore",method = RequestMethod.GET)
    public double addScore(@RequestParam("score")String score,@RequestParam("bookScore")String bookScore){
        double bookscore=( Double.parseDouble(score)*2+Double.parseDouble(bookScore))/2;

        return (double)Math.round(bookscore*10)/10;

    }
}

//    @GetMapping("/review-listing")
//    public String viewReviewList(){
//        return "review/review-listing";
//    }
//
//    @GetMapping("/review-detail")
//    public String viewReview(){
//        return "review/review-detail";
//    }

//    @GetMapping("/cart")
//    public String viewCart(){
//        return "cart/cart";
//    }