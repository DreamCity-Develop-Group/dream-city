package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wvv
 *
 */
@RestController
@RequestMapping("/message")
public class MassegeController {

    private static final Logger logger = LoggerFactory.getLogger(MassegeController.class);


    @Autowired
    MessageService messageService;


    @RequestMapping("/insertMessage")
    public Result<Integer> insertMessage(@RequestBody CityMessage record){
        boolean success = Boolean.FALSE;
        try {
            int i = messageService.insertMessage(record);
            if (i>0) {
                success = Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error("新增信息异常",e);
        }

        return Result.result(success,"新增信息",
                success? ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus()
                );
    }

    @RequestMapping("/updateMessageById")
    public Result<Integer> updateMessageById(@RequestBody CityMessage record){
        boolean success = Boolean.FALSE;
        try {
            int i = messageService.updateMessageById(record);
            if (i > 0) {
                success = Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error("更新信息异常",e);
        }
        return Result.result(success,"更新信息",
                success? ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus()
                );
    }

    @RequestMapping("/updateMessageHaveReadById")
    public Result<Integer> updateMessageHaveReadById(@RequestBody CityMessage record){
        boolean success = Boolean.FALSE;
        try {
            int i = messageService.updateMessageHaveReadById(record);
            if (i>0) {
                success = Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error("更新信息为已读异常",e);
        }
        return Result.result(success,"更新信息为已读",
                success? ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus()
                );
    }

    @RequestMapping("/deleteMessageById/{id}")
    public Result<Integer> deleteMessageById(@PathVariable("id") Long id){
        boolean success = Boolean.FALSE;
        try {
            int i = messageService.deleteMessageById(id);
            if (i>0) {
                success = Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error("删除信息异常",e);
        }
        return Result.result(success,"删除信息",
                success? ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus()
        );
    }

    @RequestMapping("/getMessageById/{id}")
    public Result<CityMessage> getMessageById(@PathVariable("id") Long id){
        boolean success = Boolean.TRUE;
        CityMessage message = null;
        try {
            message = messageService.getMessageById(id);
        }catch (Exception e){
            success = Boolean.FALSE;
            logger.error("查询信息异常",e);
        }
        return Result.result(success,"查询信息",
                success? ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus(),
                message
        );
    }

    @RequestMapping("/getCityMessageList")
    public Result<List<CityMessage>> getCityMessageList(@RequestBody CityMessage record){
        boolean success = Boolean.TRUE;
        List<CityMessage> message = null;
        try {
            message = messageService.getCityMessageList(record);
        }catch (Exception e){
            success = Boolean.FALSE;
            logger.error("查询信息异常",e);
        }
        return Result.result(success,"查询信息",
                success? ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus(),
                message);
    }

}
