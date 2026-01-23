package com.ordering.system.controller;

import com.ordering.system.common.Result;
import com.ordering.system.entity.User;
import com.ordering.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.ordering.system.mapper.UserMapper;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public Result<List<User>> getAllUsers() {
        return Result.success(userMapper.findAll());
    }

    @PostMapping("/users/{userId}/authorize")
    public Result<Void> authorizeUser(@PathVariable Long userId) {
        userService.authorizeUser(userId);
        return Result.success();
    }

    @PostMapping("/users/{userId}/unauthorize")
    public Result<Void> unauthorizeUser(@PathVariable Long userId) {
        userService.unauthorizedUser(userId);
        return Result.success();
    }
}
