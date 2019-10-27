package com.dream.city.service.consumer;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.Notice;

import java.util.List;

public interface ConsumerNoticeService {
    List<Notice> getCacheNotices() throws BusinessException;
}
