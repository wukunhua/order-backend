package com.ordering.system.mapper;

import com.ordering.system.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface OrderMapper {
    Order findById(@Param("id") Long id);

    List<Order> findByUserId(@Param("userId") Long userId);

    int insert(Order order);

    int update(Order order);

    List<Order> findTodayOrderList(@Param("userId") Long userId);

    List<Order> findAllOrderList(@Param("userId") long userId);
}
