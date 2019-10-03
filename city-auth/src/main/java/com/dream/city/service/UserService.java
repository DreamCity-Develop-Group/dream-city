package com.dream.city.service;

import com.dream.city.base.model.entity.User;

import java.util.List;

/**
 * @author Wvv
 */
public interface UserService {

    User getById(int id);

    List<User> getUsers();
}
