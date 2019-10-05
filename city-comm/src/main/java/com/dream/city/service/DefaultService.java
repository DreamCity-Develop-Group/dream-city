package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.service.impl.DefaultServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wvv
 */
@FeignClient(value = "city-gateway", fallback= DefaultServiceImpl.class)
public interface DefaultService {
    /**
     * 任务添加
     */
    @RequestMapping("/job/add")
    Result jobAdd(@RequestBody Message message);

}
