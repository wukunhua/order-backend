package com.ordering.system.dto;

import lombok.Data;

@Data
public class WechatLoginDTO {
    private String code;
    private String nickname;
    private String avatar;
}
