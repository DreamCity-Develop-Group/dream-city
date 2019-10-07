package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {
    int deleteById(Integer id);

    int insertRole(Role record);

    Role getById(Integer id);

    int updateById(Role record);

    List<Role> getList();

    /**
     * 根据用户id获取角色列表
     */
    List<Role> getListByUserId(Integer userId);

    int getCount(Map param);
}