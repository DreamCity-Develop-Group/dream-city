package com.dream.city.service;

import com.dream.city.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wvv
 */
@Repository
public interface UserService {

    boolean saveUser(User user);

    void deleteUser(Integer uId);

    User updateUser(User user);

    List<User> getUsers();

}
