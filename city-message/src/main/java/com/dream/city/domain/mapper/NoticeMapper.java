package com.dream.city.domain.mapper;


import com.dream.city.base.model.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    Integer deleteByPrimaryKey(Integer noticeId);

    Integer insert(Notice record);

    Integer insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    Integer updateByPrimaryKeySelective(Notice record);

    Integer updateByPrimaryKey(Notice record);

    List<Notice> getNoticeList(Notice record);
}