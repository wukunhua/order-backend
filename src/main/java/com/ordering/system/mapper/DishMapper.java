package com.ordering.system.mapper;

import com.ordering.system.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishMapper {
    List<Dish> findByCategoryId(@Param("categoryId") Long categoryId);

    List<Dish> findAll();

    Dish findById(@Param("id") Long id);

    int insert(Dish dish);

    int update(Dish dish);

    int deleteById(@Param("id") Long id);
}
