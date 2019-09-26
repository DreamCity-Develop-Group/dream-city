package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityFile;
import com.dream.city.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/set/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileService fileService;



    @RequestMapping("/deleteFileById/{id}")
    public Result<Integer> deleteFileById(@PathVariable("id") Long id){
        logger.info("删除文件，{}", id);
        String desc = "删除文件失败";
        boolean success = Boolean.FALSE;
        CityFile file = null;
        int i = 0;
        try {
            file = fileService.getFileById(id);
            i = fileService.deleteFileById(id);
            desc ="["+ file.getFileName()+"删除文件成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            success = Boolean.FALSE;
            desc = "["+ file.getFileName()+"]删除文件异常";
            logger.error(desc,e);
        }
        return new Result<Integer>(success,desc,i);
    }

    @RequestMapping("/insertFile")
    public Result<CityFile> insertFile(@RequestBody CityFile record){
        logger.info("新增文件，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "新增文件失败";
        CityFile file = null;
        try {
            file = fileService.insertFile(record);
            desc = "["+ file.getFileName() + "]新增文件成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            success = Boolean.FALSE;
            desc = "["+ file.getFileName() + "]新增文件异常";
            logger.error(desc,e);
        }
        return  new Result<CityFile>(success,desc,file);
    }

    @RequestMapping("/getFileById/{id}")
    public Result<CityFile> getFileById(@PathVariable("id") Long id){
        logger.info("查询文件，{}", id);
        boolean success = Boolean.FALSE;
        String desc = "查询文件失败";
        CityFile file = null;
        try {
            file = fileService.getFileById(id);
            desc = "查询文件成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            success = Boolean.FALSE;
            desc = "查询文件异常";
            logger.error(desc,e);
        }
        return new Result<CityFile>(success,desc,file);
    }

    @RequestMapping("/getFileList")
    public Result<List<CityFile>> getFileList(@RequestBody CityFile record){
        logger.info("查询文件列表，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "查询文件列表失败";
        List<CityFile> fileList = null;
        try {
            fileList = fileService.getFileList(record);
            desc = "查询文件列表成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            success = Boolean.FALSE;
            desc = "查询文件列表异常";
            logger.error(desc,e);
        }
        return new Result<List<CityFile>>(success,desc,fileList);
    }

    @RequestMapping("/updateFileById")
    public Result<CityFile> updateFileById(@RequestBody CityFile record){
        logger.info("修改文件，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "修改文件失败";
        CityFile cityFile = null;
        try {
            cityFile = fileService.updateFileById(record);
            desc = "修改文件成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            success = Boolean.FALSE;
            desc = "修改文件失败异常";
            logger.error(desc,e);
        }
        return new Result<CityFile>(success,desc,cityFile);
    }



}
