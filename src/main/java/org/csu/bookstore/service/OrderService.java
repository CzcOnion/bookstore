package org.csu.bookstore.service;

import org.csu.bookstore.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    List<Order> getOrderList(int userID);

    @Transactional
    void insertOrder(Order order);

    @Transactional
    Order getOrder(int orderId);

    @Transactional
    void updateOrder(Order order);

}
