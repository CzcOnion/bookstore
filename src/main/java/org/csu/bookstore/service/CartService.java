package org.csu.bookstore.service;

import org.csu.bookstore.domain.CartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {
    @Transactional
    void addCartItem(CartItem cartItem);

    @Transactional
    void deleteCartItem(CartItem cartItem);

    List<CartItem> getCartItem(int userId);
}
