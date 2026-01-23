package com.ordering.system.controller;

import com.ordering.system.common.Result;
import com.ordering.system.entity.Dish;
import com.ordering.system.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public Result<List<Dish>> findAll() {
        return Result.success(dishService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Dish> findById(@PathVariable Long id) {
        Dish dish = dishService.findById(id);
        if (dish == null) {
            return Result.error("菜品不存在");
        }
        return Result.success(dish);
    }

    @GetMapping("/category/{categoryId}")
    public Result<List<Dish>> findByCategoryId(@PathVariable Long categoryId) {
        return Result.success(dishService.findByCategoryId(categoryId));
    }

    @PostMapping
    public Result<Dish> create(@RequestBody Dish dish) {
        return Result.success(dishService.create(dish));
    }

    @PutMapping("/{id}")
    public Result<Dish> update(@PathVariable Long id, @RequestBody Dish dish) {
        Dish existing = dishService.findById(id);
        if (existing == null) {
            return Result.error("菜品不存在");
        }
        dish.setId(id);
        return Result.success(dishService.update(dish));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        dishService.delete(id);
        return Result.success();
    }
}
