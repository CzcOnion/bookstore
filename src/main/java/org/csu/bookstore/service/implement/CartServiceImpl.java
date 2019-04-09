package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.CartItem;
import org.csu.bookstore.domain.CartItemKey;
import org.csu.bookstore.persistence.CartItemMapper;
import org.csu.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemMapper cartMapper;


    @Override
    public void addCartItem(CartItem cartItem) {
        CartItemKey cartItemKey = new CartItemKey();
        cartItemKey.setBookId(cartItem.getBookId());
        cartItemKey.setUserId(cartItem.getUserId());
        CartItem cartItem1 = cartMapper.selectByPrimaryKey(cartItemKey);
        if(cartItem1 != null)
        {
            cartItem.setCount(cartItem.getCount()+cartItem1.getCount());
            cartMapper.updateByPrimaryKey(cartItem);
        }
        else{
            cartMapper.insert(cartItem);
        }
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        cartMapper.deleteByPrimaryKey(cartItem);
    }

    @Override
    public List<CartItem> getCartItem(int userId) {
        return cartMapper.selectByUserId(userId);
    }
}
