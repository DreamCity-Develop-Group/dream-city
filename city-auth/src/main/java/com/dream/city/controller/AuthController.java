package com.dream.city.controller;

import com.dream.city.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    /**
     * 获得token
     * @param username
     * @return
     */
    @RequestMapping("/get/{username}")
    public String getAuth(@PathVariable("username") String username){
        String token = authService.generiteToken(username);

        return token;
    }

    /**
     * 查看token对应的用户名
     * @param token
     * @return
     */
    @RequestMapping("/token/{token}")
    public String getUser(@PathVariable("token")String token){
        String user = authService.getUsername(token);

        return user;
    }

    @RequestMapping("/valid/{username}")
    public String validUser(@PathVariable("username") String username){
        return authService.valiUser(username);
    }

}
