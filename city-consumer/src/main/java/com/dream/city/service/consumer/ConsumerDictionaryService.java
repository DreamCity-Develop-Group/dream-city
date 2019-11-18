package com.dream.city.service.consumer;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "city-trade")
public interface ConsumerDictionaryService {


    /**
     * @param key
     * @return
     */
    @RequestMapping("/dict/getValByKey")
    String getValByKey(@RequestParam("key") String key);

}