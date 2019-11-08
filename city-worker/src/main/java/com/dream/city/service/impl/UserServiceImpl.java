package com.dream.city.service.impl;

import com.dream.city.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Wvv
 */
@Service
public class UserServiceImpl  implements UserService {
    public void play(){
        System.out.println("user is play.");
    }

    public void study(){
        System.out.println("User is study.");
    }
}
