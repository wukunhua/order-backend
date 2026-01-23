package com.ordering.system.service;

import com.ordering.system.entity.Category;
import com.ordering.system.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

    public Category create(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insert(category);
        return category;
    }

    public Category update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
        return category;
    }

    public void delete(Long id) {
        categoryMapper.deleteById(id);
    }
}
