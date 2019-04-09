package org.csu.bookstore.controller;

import org.csu.bookstore.domain.*;
import org.csu.bookstore.service.CartService;
import org.csu.bookstore.service.CatalogService;
import org.csu.bookstore.service.OrderService;
import org.csu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CheckOutController {

    private User user = new User();

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CatalogService catalogService;

    @ModelAttribute("user")
    public User user(){
        return user;
    }

    @ModelAttribute("itemList")
    public List<Item> itemList(){
        return catalogService.getItem();
    }

    private Order order=new Order();

    @RequestMapping(value = "/newOrder" ,method = RequestMethod.POST)
    public String address(@RequestParam("zcity") String city, HttpSession session){
        User user=(User)session.getAttribute("user");
        List<CartItem> cartItems=cartService.getCartItem(user.getUserId());
        List<OrderDetail> orderDetailList=new ArrayList<>();

        for(int i=0;i<cartItems.size();i++){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setCount(cartItems.get(i).getCount());
            orderDetail.setBookId(cartItems.get(i).getBookId());
            orderDetailList.add(orderDetail);
            cartService.deleteCartItem(cartItems.get(i));
        }

        order.setCarriage( 10.0f);
        String totalCost=(String)session.getAttribute("totalCost");
        order.setTotalCost(Float.parseFloat(totalCost.substring(1)));
        order.setDate(new Date());
        userService.getUser(user.getUserId());
        order.setRecipientName(user.getName());
        order.setRecipientPhone(user.getPhone());
        order.setRecipientAddress(city);
        order.setIsDone((short)0);
        order.setIsPaid((short)0);
        order.setIsRecived((short)0);
        order.setIsSent((short)0);
        order.setOrderDetailList(orderDetailList);
        order.setUserId(((User) session.getAttribute("user")).getUserId());
        orderService.insertOrder(order);



        System.out.println(city);

        return "index";
    }
}
