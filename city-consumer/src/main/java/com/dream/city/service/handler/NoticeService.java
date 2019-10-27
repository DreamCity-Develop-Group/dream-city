package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getCacheNotices()  throws BusinessException;
}
