package com.ordering.system.service;

import com.ordering.system.entity.User;
import com.ordering.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public User findByOpenId(String openId) {
        return userMapper.findByOpenId(openId);
    }

    public User createOrUpdateUser(String openId, String nickname, String avatar) {
        User user = userMapper.findByOpenId(openId);
        if (user == null) {
            user = new User();
            user.setOpenId(openId);
            user.setNickname(nickname);
            user.setAvatar(avatar);
            user.setAuthorized(false);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.insert(user);
        } else {
            user.setNickname(nickname);
            user.setAvatar(avatar);
            user.setUpdateTime(LocalDateTime.now());
            userMapper.update(user);
        }
        return user;
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public void authorizeUser(Long userId) {
        User user = userMapper.findById(userId);
        if (user != null) {
            user.setAuthorized(true);
            user.setUpdateTime(LocalDateTime.now());
            userMapper.update(user);
        }
    }

    public void unauthorizedUser(Long userId) {
        User user = userMapper.findById(userId);
        if (user != null) {
            user.setAuthorized(false);
            user.setUpdateTime(LocalDateTime.now());
            userMapper.update(user);
        }
    }
}
