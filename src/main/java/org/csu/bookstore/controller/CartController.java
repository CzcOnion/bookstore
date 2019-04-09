package org.csu.bookstore.controller;

import org.csu.bookstore.domain.Book;
import org.csu.bookstore.domain.CartItem;
import org.csu.bookstore.domain.Item;
import org.csu.bookstore.domain.User;
import org.csu.bookstore.service.CartService;
import org.csu.bookstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    private User user = new User();

    @Autowired
    private CartService cartService;

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


    @GetMapping("/cart")
    public String viewCart(Model model,HttpSession session){
        List<Item> itemList = catalogService.getItem();
        List<Book> bookList = catalogService.getBookList(1000002);
        model.addAttribute("bookList",bookList);
        model.addAttribute("itemList",itemList);

        User user=(User)session.getAttribute("user");
        List<CartItem> cartItems=cartService.getCartItem(user.getUserId());
        Book book ;
        for (int i=0;i<cartItems.size();i++){
            book=catalogService.getBook(cartItems.get(i).getBookId());
            cartItems.get(i).setBook(book);
            // catalogService.getBook(cartItems.get(i).getBookId());
        }
        model.addAttribute("cart",cartItems);
        return "cart/cart";
    }

    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public String addCart(@RequestParam("bookId")String bookId,@RequestParam("count")String Count,Model model,HttpSession session){
        CartItem cartItem=new CartItem();
        User user=(User)session.getAttribute("user");
        int bookIdd=Integer.parseInt(bookId);
        int count=Integer.parseInt(Count);
        cartItem.setBookId(bookIdd);
        cartItem.setCount(count);
        cartItem.setUserId(user.getUserId());

        cartService.addCartItem(cartItem);
        return "redirect:/cart";
    }

    @ResponseBody
    @GetMapping("/checkout")
    public boolean checkOut(@RequestParam("totalCost") String cost, HttpSession session){
        session.setAttribute("totalCost",cost);
        return true;
    }

    @GetMapping("/viewCheckout")
    public String checkOut(){
        return "cart/checkout";
    }

}
