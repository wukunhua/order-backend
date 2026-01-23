package com.ordering.system.controller;

import com.ordering.system.common.PageResult;
import com.ordering.system.common.Result;
import com.ordering.system.dto.OrderDTO;
import com.ordering.system.entity.Order;
import com.ordering.system.entity.OrderItem;
import com.ordering.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Result<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(orderDTO.getUserId(), orderDTO.getDishNames());
        return Result.success(order);
    }

    @GetMapping("/{id}")
    public Result<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        return Result.success(order);
    }

    @GetMapping("/user/{userId}")
    public Result<List<Order>> findByUserId(@PathVariable Long userId) {
        return Result.success(orderService.findByUserId(userId));
    }

    @GetMapping("/{orderId}/items")
    public Result<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
        return Result.success(orderService.getOrderItems(orderId));
    }

    @GetMapping("/todayorder/{userId}")
    public Result<List<OrderItem>> getTodayOrderList(@PathVariable Long userId){

        return Result.success(orderService.getTodayOrderList(userId));
    }

    @GetMapping("/history/{userId}")
    public Result getHistoryOrderList(@PathVariable Long userId, @RequestParam Integer pageNum, @RequestParam Integer pageSize){

        return Result.success(orderService.getAllOrderList(userId,pageNum,pageSize));
    }
}
