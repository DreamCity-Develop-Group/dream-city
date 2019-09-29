package com.dream.city.service;


import com.dream.city.base.model.entity.Notice;

import java.util.List;

public interface NoticeService {

    int deleteNoticeById(Integer noticeId);

    int insertNoticeById(Notice record);

    Notice getNoticeById(Integer noticeId);

    List<Notice> getNoticeList(Notice record);

    int updateNoticeById(Notice record);

}