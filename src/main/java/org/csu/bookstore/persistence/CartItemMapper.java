package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.CartItem;
import org.csu.bookstore.domain.CartItemKey;

import java.util.List;

public interface CartItemMapper {
    int deleteByPrimaryKey(CartItemKey key);

    int insert(CartItem record);

    int insertSelective(CartItem record);

    CartItem selectByPrimaryKey(CartItemKey key);

    int updateByPrimaryKeySelective(CartItem record);

    int updateByPrimaryKey(CartItem record);

    List<CartItem> selectByUserId(int userId);
}