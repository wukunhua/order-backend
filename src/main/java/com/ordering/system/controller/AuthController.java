package com.ordering.system.controller;

import com.ordering.system.common.Result;
import com.ordering.system.dto.WechatLoginDTO;
import com.ordering.system.entity.User;
import com.ordering.system.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Value("${wechat.miniapp.app-id}")
    private String appId;

    @Value("${wechat.miniapp.app-secret}")
    private String appSecret;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody WechatLoginDTO loginDTO) {
        try {
            // 调用微信接口获取openid
            String url = String.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                appId, appSecret, loginDTO.getCode()
            );

            String response = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            JsonNode jsonNode = objectMapper.readTree(response);
            String openId = jsonNode.get("openid").asText();

            // 创建或更新用户
            User user = userService.createOrUpdateUser(openId, loginDTO.getNickname(), loginDTO.getAvatar());

            Map<String, Object> data = new HashMap<>();
            data.put("userId", user.getId());
            data.put("authorized", user.getAuthorized());

            return Result.success(data);
        } catch (Exception e) {
            return Result.error("登录失败：" + e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public Result<User> getUserInfo(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }
}
