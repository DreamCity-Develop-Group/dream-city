package com.dream.city.controller;

import com.dream.city.base.model.entity.User;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/index") //在类上使用RequestMapping，里面设置的value就是方法的父路径
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hi")  //如果方法上的RequestMapping没有value，则此方法默认被父路径调用
    public String index() {
        return "hello spring boot";
    }

    @RequestMapping("/users/{userId}")
    public User getUser(@PathVariable("userId") int userId) {
        User user = new User();
        user = userService.getById(userId);
        return user;
    }

    @RequestMapping("/users/get")
    public List getUsers() {
        List<User> users = new ArrayList<>();
        users = userService.getUsers();
        return users;
    }
}
