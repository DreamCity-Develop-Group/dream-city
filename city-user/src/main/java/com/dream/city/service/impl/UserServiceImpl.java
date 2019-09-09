package com.dream.city.service.impl;

import com.dream.city.domain.mapper.UserMapper;
import com.dream.city.domain.entity.User;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public void deleteUser(Integer uId) {
        System.out.println("["+appName+"]Delete user"+uId);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }
}
