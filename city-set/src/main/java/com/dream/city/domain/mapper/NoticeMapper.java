package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.Notice;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wvv
 */
@Repository
public interface NoticeMapper {

    @ResultMap("NoticeBaseResultMap")
    @Select("select * from game_notice where 1=1 and notice_state=#{isValid}")
    List<Notice> getGameNotices(int state);
}
