package com.dream.city.controller;


import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/notice")
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    NoticeService noticeService;



    @RequestMapping("/deleteNoticeById/{noticeId}")
    public Result<Integer> deleteNoticeById(@PathVariable("noticeId") Integer noticeId){
        boolean success = Boolean.FALSE;
        try {
            int i = noticeService.deleteNoticeById(noticeId);
            if (i>0) {
                success = Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error("删除通知异常",e);
        }

        return new Result(success,"删除通知");
    }

    @RequestMapping("/insertNoticeById")
    public Result<Integer> insertNoticeById(@RequestBody Notice record){
        boolean success = Boolean.FALSE;
        try {
            int i = noticeService.insertNoticeById(record);
            if (i>0) {
                success = Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error("新增通知异常",e);
        }

        return new Result(success,"新增通知");
    }

    @RequestMapping("/getNoticeById/{noticeId}")
    public Result<Notice> getNoticeById(@PathVariable("noticeId") Integer noticeId){
        boolean success = Boolean.FALSE;
        Notice i = null;
        try {
            i = noticeService.getNoticeById(noticeId);
            success = Boolean.TRUE;
        }catch (Exception e){
            logger.error("查询通知异常",e);
        }

        return new Result(success,"查询通知",i);
    }

    @RequestMapping("/getNoticeList")
    public Result<List<Notice>> getNoticeList(@RequestBody Notice record){
        boolean success = Boolean.FALSE;
        List<Notice> i = null;
        try {
            i = noticeService.getNoticeList(record);
            success = Boolean.TRUE;
        }catch (Exception e){
            logger.error("获取通知列表异常",e);
        }

        return new Result(success,"获取通知列表",i);
    }

    @RequestMapping("/updateNoticeById")
    public Result<Integer> updateNoticeById(@RequestBody Notice record){
        boolean success = Boolean.FALSE;
        try {
            int i = noticeService.updateNoticeById(record);
            if (i>0) {
                success = Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error("更新通知异常",e);
        }

        return new Result(success,"更新通知");
    }

}