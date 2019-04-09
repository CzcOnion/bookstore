package org.csu.bookstore.persistence;

import org.csu.bookstore.domain.OrderDetail;
import org.csu.bookstore.domain.OrderDetailKey;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(OrderDetailKey key);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(OrderDetailKey key);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    int getNextId();

    List<OrderDetail> selectByOrderId(Integer orderId);
}