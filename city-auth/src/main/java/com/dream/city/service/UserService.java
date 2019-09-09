package com.dream.city.service;

import com.dream.city.domain.entity.User;
import com.dream.city.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wvv
 */
public interface UserService {

    User getById(int id);

    List<User> getUsers();
}
