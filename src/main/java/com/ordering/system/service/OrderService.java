package com.ordering.system.service;

import com.ordering.system.common.PageResult;
import com.ordering.system.entity.Order;
import com.ordering.system.entity.OrderItem;
import com.ordering.system.mapper.OrderMapper;
import com.ordering.system.mapper.OrderItemMapper;
import com.ordering.system.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 自定义分页结果DTO（推荐，比PageInfo更灵活）




@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    public Order createOrder(Long userId, List<String> dishNames) {
        Order order = new Order();
        order.setUserId(userId);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderMapper.insert(order);

        for (String dishName : dishNames) {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setDishName(dishName);
            orderItemMapper.insert(item);
        }

        return order;
    }

    public Order findById(Long id) {
        return orderMapper.findById(id);
    }

    public List<Order> findByUserId(Long userId) {
        return orderMapper.findByUserId(userId);
    }

    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }

    public List<OrderItem> getTodayOrderList(Long userId) {
        //找到今天的所有菜单
        List<Order> todayOrderList = orderMapper.findTodayOrderList(userId);
        //循环找到所有菜单中的菜
        List<OrderItem> allData = new ArrayList<>();
        for(Order order : todayOrderList){
            List<OrderItem> byOrderId = orderItemMapper.findByOrderId(order.getId());
            if (byOrderId != null && !byOrderId.isEmpty()) {
                allData.addAll(byOrderId);
            }
        }
        return allData;
    }

    public PageResult<OrderItem> getAllOrderList(Long userId, Integer pageNum, Integer  pageSize) {
        // 1. 参数校验（避免非法值）
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        int offset = (pageNum - 1) * pageSize;

        // 2. 数据库层分页查询菜品
        List<OrderItem> dishList = orderItemMapper.findByUserIdWithPage(userId, offset, pageSize);
        // 3. 查询菜品总条数
        Long total = orderItemMapper.countByUserId(userId);

        // 4. 封装分页结果
        return new PageResult<OrderItem>(dishList, total, pageNum, pageSize);
    }


}
