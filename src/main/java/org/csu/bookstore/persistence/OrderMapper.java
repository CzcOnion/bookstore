package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int getNextId();

    List<Order> selectByUserId(Integer userId);
}