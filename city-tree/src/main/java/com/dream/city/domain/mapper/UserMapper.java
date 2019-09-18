package com.dream.city.domain.mapper;

import com.dream.city.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getById(int id);
    boolean insert(String name);
    List<User> getUsers();
    boolean updateUser(User user);
    boolean deleteUser(int id);
    boolean deleteAllUsers();
}
