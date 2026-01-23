package com.ordering.system.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Dish {
    private Long id;
    private String name;
    private Long categoryId;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
