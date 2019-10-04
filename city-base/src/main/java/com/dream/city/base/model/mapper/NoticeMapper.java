package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wvv
 */
@Mapper
public interface NoticeMapper {

    Integer deleteByPrimaryKey(Integer noticeId);

    Integer insert(Notice record);

    Integer insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    Integer updateByPrimaryKeySelective(Notice record);

    Integer updateByPrimaryKey(Notice record);

    List<Notice> getNoticeList(Notice record);

    @Select("select * from game_notice where 1=1 and notice_state=#{isValid}")
    List<Notice> getGameNotices(int state);
}