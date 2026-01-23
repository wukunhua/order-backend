package com.ordering.system.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private Long userId;
    private List<String> dishNames;
}
