package org.csu.bookstore.service.implement;

import org.csu.bookstore.domain.Book;
import org.csu.bookstore.domain.Order;
import org.csu.bookstore.domain.OrderDetail;
import org.csu.bookstore.persistence.BookMapper;
import org.csu.bookstore.persistence.ImageMapper;
import org.csu.bookstore.persistence.OrderDetailMapper;
import org.csu.bookstore.persistence.OrderMapper;
import org.csu.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<Order> getOrderList(int userId){

        List<Order> orderList = orderMapper.selectByUserId(userId);
        for(int i=0;i<orderList.size();i++)
        {
            List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderList.get(i).getOrderId());
            for(int k=0;k<orderDetailList.size();k++)
            {
                Book book = bookMapper.selectByPrimaryKey(orderDetailList.get(k).getBookId());
                book.setImageList(imageMapper.selectByBookName(book.getName()));
                orderDetailList.get(k).setBook(book);
            }
            orderList.get(i).setOrderDetailList(orderDetailList);
        }
        return orderList;
    }


    @Override
    public void insertOrder(Order order){
        //获取orderId
        try {
            order.setOrderId(orderMapper.getNextId());
        }
        catch (Exception e){
            int orderId = 1000000;
            order.setOrderId(orderId);
        }
        orderMapper.insert(order);
        for(int i = 0;i<order.getOrderDetailList().size();i++)
        {
            //存入订单明细
            OrderDetail orderDetail = order.getOrderDetailList().get(i);
            orderDetail.setOrderId(order.getOrderId());
            orderDetailMapper.insert(orderDetail);
            //书本数量更新
            Book book = bookMapper.selectByPrimaryKey(orderDetail.getBookId());
            book.setQuantity(book.getQuantity()-orderDetail.getCount());
        }
    }

    @Override
    public Order getOrder(int orderId){
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setOrderDetailList(orderDetailMapper.selectByOrderId(orderId));
        return order;
    }

    @Override
    public void updateOrder(Order order){
        orderMapper.updateByPrimaryKey(order);
    }

}
