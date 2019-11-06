package com.dream.city.service.impl;

import com.dream.city.base.model.entity.User;
import com.dream.city.base.model.mapper.UserMapper;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(int id){
        return userMapper.getById(id);
    }
    public List<User> getUsers(){
        return userMapper.getUsers();
    }
}
