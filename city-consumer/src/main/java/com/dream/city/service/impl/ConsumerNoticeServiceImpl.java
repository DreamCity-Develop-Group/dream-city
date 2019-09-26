package com.dream.city.service.impl;

import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Service
@Slf4j
public class ConsumerNoticeServiceImpl{
    @Autowired
    RedisUtils redisUtils;

    public List<Notice> getCacheNotices(){
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
