package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.req.NoticeReq;
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


    Integer insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    Integer updateByPrimaryKeySelective(Notice record);


    List<Notice> getNoticeList(NoticeReq record);

    @Select("select * from city_notice where 1=1 and notice_state=#{isValid}")
    List<Notice> getGameNotices(int state);
}