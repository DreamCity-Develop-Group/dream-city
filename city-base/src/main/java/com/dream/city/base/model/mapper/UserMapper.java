package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper{
    User getById(int id);
    boolean insert(String name);
    List<User> getUsers();
    boolean updateUser(User user);
    boolean deleteUser(int id);
    boolean deleteAllUsers();
}
