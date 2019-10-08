package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper{

    User getById(int id);

    List<User> getUsers();

    int deleteByUuid(String uuid);

    int insertUser(User user);

    int updateById(User user);

    List<User> getList(Map param);

    Integer getCount(Map param);

    User findUserByName(String loginName);
}
