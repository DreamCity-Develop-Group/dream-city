package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityFile;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerFileController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerFileService fileService;



    @RequestMapping("deleteFileById")
    public Message deleteFileById(@RequestBody Message msg){
        logger.info("删除文件，{}", msg);

        String desc = "删除文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> getFileByIdResult = fileService.getFileById(cityFile.getId());
            file = getFileByIdResult.getData();
            Result<Integer> deleteFileByIdResult = fileService.deleteFileById(cityFile.getId());
            deleteFileByIdResult.getData();
            desc ="["+ file.getFileName()+"删除文件成功";
        }catch (Exception e){
            desc = "["+ file.getFileName()+"]删除文件异常";
            logger.error(desc,e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return msg;
    }

    @RequestMapping("/insertFile")
    public Message insertFile(@RequestBody Message msg){
        logger.info("新增文件，{}", msg);
        String desc = "新增文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> insertFileResult = fileService.insertFile(cityFile);
            file = insertFileResult.getData();
            desc = "["+ file.getFileName() + "]新增文件成功";
        }catch (Exception e){
            desc = "["+ file.getFileName() + "]新增文件异常";
            logger.error(desc,e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return  msg;
    }

    @RequestMapping("/getFileById/{id}")
    public Message getFileById(@RequestBody Message msg){
        logger.info("查询文件，{}", msg);
        String desc = "查询文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> fileResult = fileService.getFileById(cityFile.getId());
            file = fileResult.getData();
            desc = "查询文件成功";
        }catch (Exception e){
            desc = "查询文件异常";
            logger.error(desc,e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return msg;
    }

    @RequestMapping("/getFileList")
    public Message getFileList(@RequestBody Message msg){
        logger.info("查询文件列表，{}", msg);
        String desc = "查询文件列表失败";
        List<CityFile> fileList = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<List<CityFile>> listResult = fileService.getFileList(cityFile);
            fileList = listResult.getData();
            desc = "查询文件列表成功";
        }catch (Exception e){
            desc = "查询文件列表异常";
            logger.error(desc,e);
        }
        msg.getData().setData(fileList);
        msg.setDesc(desc);
        return msg;
    }

    @RequestMapping("/updateFileById")
    public Message updateFileById(@RequestBody Message msg){
        logger.info("修改文件，{}", msg);
        String desc = "修改文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> fileResult = fileService.updateFileById(cityFile);
            file = fileResult.getData();
            desc = "修改文件成功";
        }catch (Exception e){
            desc = "修改文件异常";
            logger.error(desc,e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return msg;
    }



}
