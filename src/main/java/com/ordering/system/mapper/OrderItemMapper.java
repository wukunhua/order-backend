package com.ordering.system.mapper;

import com.ordering.system.entity.Order;
import com.ordering.system.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);

    int insert(OrderItem orderItem);

    int deleteById(@Param("id") Long id);

    List<OrderItem> findAll(@Param("userId") Long id,@Param("pageNum") Number pageNum,@Param("pageSize") Number pageSize);

    /**
     * 分页查询指定用户的所有菜品
     * @param userId 用户ID
     * @param offset 分页偏移量 (pageNum-1)*pageSize
     * @param pageSize 每页条数
     * @return 分页后的菜品列表
     */
    List<OrderItem> findByUserIdWithPage(
            @Param("userId") Long userId,
            @Param("offset") Integer offset,
            @Param("pageSize") Integer pageSize
    );

    /**
     * 查询指定用户的菜品总数
     * @param userId 用户ID
     * @return 菜品总条数
     */
    Long countByUserId(@Param("userId") Long userId);
}
