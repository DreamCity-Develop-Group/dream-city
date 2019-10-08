package com.dream.city.service;


import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.entity.RelationTree;

import java.util.List;

/**
 * @author Wvv
 */
public interface NoticeService {

    int deleteNoticeById(Integer noticeId);

    int insertNoticeById(Notice record);

    Notice getNoticeById(Integer noticeId);

    List<Notice> getNoticeList(Notice record);

    int updateNoticeById(Notice record);

    List<RelationTree> testMapper();

}