package com.dream.city.service.handler.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.handler.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 * @program: dream-city
 * @File: NoticeServiceImpl
 * @description: 公告消息服务
 * @create: 2019/10/2019/10/28 00:44:55 [星期一]
 **/
@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    RedisUtils redisUtils;

    @LcnTransaction
    @Transactional
    @Override
    public List<Notice> getCacheNotices()  throws BusinessException {
        List<Notice> notices = new ArrayList<>();
        Map<Object,Object> map =redisUtils.hmget("game_default");
        Iterator<Map.Entry<Object, Object>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            Notice notice = (Notice) entry.getValue();
            log.info(notice.getNoticeContent());
            notices.add(notice);
        }

        return notices;
    }
}
