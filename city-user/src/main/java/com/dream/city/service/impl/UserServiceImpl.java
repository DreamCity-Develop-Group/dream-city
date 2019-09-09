package com.dream.city.service.impl;

import com.dream.city.domain.mapper.UserMapper;
import com.dream.city.domain.entity.User;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        return userMapper.save(user)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public void deleteUser(int playerId) {
        userMapper.deleteById(playerId);
    }

    @Override
    public User updateUser(User user) {
        userMapper.update(user);
        return user;
    }

    @Override
    public List<User> getUsers(User user) {
        List<User> players = userMapper.getUsers();

        List<User> users = new ArrayList<>();
        if (CollectionUtils.isEmpty(players)){
            return users;
        }

        players.forEach(player -> {
            users.add(player);
        });
        return users;
    }




}
