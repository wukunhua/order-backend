package com.ordering.system.mapper;

import com.ordering.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByOpenId(@Param("openId") String openId);

    int insert(User user);

    int update(User user);

    User findById(@Param("id") Long id);

    List<User> findAll();
}
