package org.csu.bookstore.controller;

import org.csu.bookstore.domain.Collection;
import org.csu.bookstore.domain.Item;
import org.csu.bookstore.domain.Order;
import org.csu.bookstore.domain.User;
import org.csu.bookstore.service.CatalogService;
import org.csu.bookstore.service.CollectionService;
import org.csu.bookstore.service.OrderService;
import org.csu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CollectionService collectionService;

    private User user = new User();

    @ModelAttribute("user")
    public User user(){
        return user;
    }

    @ModelAttribute("itemList")
    public List<Item> itemList(){
        return catalogService.getItem();
    }

    public String view(){
        return "/product/product-detail";
    }



    @ResponseBody
    @RequestMapping(value = "/login",method  = RequestMethod.POST)
    public boolean login(@ModelAttribute(value="user") User user,Model model, HttpServletRequest request , HttpSession session){
        String userId = user.getUserId()+"";
        String password = user.getPassword();
        if(!userId.isEmpty() && !user.getPassword().isEmpty())
        {
            try{
                user = userService.getUser(user.getUserId());
                if(user != null && password.equals(user.getPassword()))
                {
                    model.addAttribute("user",user);
                    session.setAttribute("user",user);
                    return true;
                }
                else
                    return false;

            }
            catch (Exception e)
            {
                System.out.println(e);
                return false;
            }
        }
        else
            return false;
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public String signIn(@ModelAttribute("user") User user,Model model,HttpSession session){
        userService.insertUser(user);
        System.out.println(user.getName());
        model.addAttribute("user",user);
        session.setAttribute("user",user);
        return "account/personal";
    }

    @GetMapping("/logoff")
    public String logoff(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

    @ResponseBody
    @GetMapping("/checkUser")
    public Boolean checkUser(HttpSession session){
        User user=(User)session.getAttribute("user");
        if (user==null)
            return false;
        else
            return true;
    }


    @ResponseBody
    @GetMapping("/addLove")
    public boolean addLove(@RequestParam("bookName")String bookName,HttpSession session){
        User user=(User)session.getAttribute("user");

        int bookid=catalogService.getBookByBookName(bookName).getBookId();
        Collection collection=new Collection();
        collection.setDate(new Date());
        collection.setBookId(bookid);
        collection.setUserId(user.getUserId());
        collectionService.insertCollection(collection);
        return true;
//        User user=(User)session.getAttribute("user");
//        if (user==null)
//            return false;
//        else
//            return true;
    }
}
