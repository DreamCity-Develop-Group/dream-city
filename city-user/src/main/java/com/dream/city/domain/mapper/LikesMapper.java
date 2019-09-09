package com.dream.city.domain.mapper;


import com.dream.city.domain.entity.Likes;

public interface LikesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
}