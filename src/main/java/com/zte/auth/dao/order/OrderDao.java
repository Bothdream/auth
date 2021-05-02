package com.zte.auth.dao.order;

import com.zte.auth.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    List<Order> listOrder(@Param("name") String name);

    int insertOrder(Order order);
}
