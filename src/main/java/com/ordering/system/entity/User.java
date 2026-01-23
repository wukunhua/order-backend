package com.ordering.system.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String openId;
    private String nickname;
    private String avatar;
    private Boolean authorized;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
