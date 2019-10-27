package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.service.handler.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerFileController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileService fileService;



    @RequestMapping("deleteFileById")
    public Message deleteFileById(@RequestBody Message msg){
        try {
            return fileService.deleteFileById(msg);
        }catch (Exception e){
            return msg;
        }
    }

    @RequestMapping("/insertFile")
    public Message insertFile(@RequestBody Message msg){
        try {
            return fileService.insertFile(msg);
        }catch (Exception e){
            return msg;
        }
    }

    @RequestMapping("/getFileById/{id}")
    public Message getFileById(@RequestBody Message msg){
        try {
            return fileService.getFileById(msg);
        }catch (Exception e){
            return msg;
        }
    }

    @RequestMapping("/getFileList")
    public Message getFileList(@RequestBody Message msg){
        try {
            return fileService.getFileList(msg);
        }catch (Exception e){
            return msg;
        }
    }

    @RequestMapping("/updateFileById")
    public Message updateFileById(@RequestBody Message msg){
        try {
            return fileService.updateFileById(msg);
        }catch (Exception e){
            return msg;
        }
    }



}
