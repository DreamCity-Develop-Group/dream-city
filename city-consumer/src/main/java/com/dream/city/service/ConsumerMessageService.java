package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Wvv
 */
@FeignClient(value = "city-message")
@RequestMapping("/message")
public interface ConsumerMessageService {

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/getCode")
    Result getCode(@RequestBody Message message);

    @RequestMapping("/checkCode")
    Result checkCode(@RequestParam("code")String code,@RequestParam("account")String account);


    /**
     * 新增消息
     * @param record
     * @return
     */
    @RequestMapping("/insertMessage")
    Result<Integer> insertMessage(@RequestBody CityMessage record);


//    @RequestMapping("/updateMessageById")
//    Result<Integer> updateMessageById(@RequestBody CityMessage record);

    /**
     * 更新为已读
     * @param record
     * @return
     */
    @RequestMapping("/updateMessageHaveReadById")
    Result<Integer> updateMessageHaveReadById(@RequestBody CityMessage record);

//    @RequestMapping("/deleteMessageById/{id}")
//    Result<Integer> deleteMessageById(@PathVariable("id") Long id);

    /**
     * 阅读消息
     * @param id
     * @return
     */
    @RequestMapping("/getMessageById/{id}")
    Result<CityMessage> getMessageById(@PathVariable("id") Long id);

    /**
     * 消息列表
     * @param record
     * @return
     */
    @RequestMapping("/getCityMessageList")
    Result<List<CityMessage>> getCityMessageList(@RequestBody CityMessage record);


    @RequestMapping("/getUnReadCount/{playerId}")
    Result<Integer> getUnReadCount(@PathVariable("playerId")String playerId);

}
