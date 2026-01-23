package com.ordering.system.service;

import com.ordering.system.entity.Dish;
import com.ordering.system.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishMapper dishMapper;

    public List<Dish> findByCategoryId(Long categoryId) {
        return dishMapper.findByCategoryId(categoryId);
    }

    public List<Dish> findAll() {
        return dishMapper.findAll();
    }

    public Dish findById(Long id) {
        return dishMapper.findById(id);
    }

    public Dish create(Dish dish) {
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.insert(dish);
        return dish;
    }

    public Dish update(Dish dish) {
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.update(dish);
        return dish;
    }

    public void delete(Long id) {
        dishMapper.deleteById(id);
    }
}
