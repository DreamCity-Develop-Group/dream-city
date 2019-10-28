package com.dream.city.service.handler.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityFile;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.consumer.ConsumerFileService;
import com.dream.city.service.handler.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wvv
 * @program: dream-city
 * @File: FileServiceImpl
 * @description: 文件服务
 * @create: 2019/10/2019/10/27 22:26:09 [星期日]
 **/

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerFileService consumerFileService;


    @LcnTransaction
    @Transactional
    @Override
    public Message deleteFileById(Message msg) throws BusinessException {
        logger.info("删除文件，{}", msg);

        String desc = "删除文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> getFileByIdResult = consumerFileService.getFileById(cityFile.getId());
            file = getFileByIdResult.getData();
            Result<Integer> deleteFileByIdResult = consumerFileService.deleteFileById(cityFile.getId());
            deleteFileByIdResult.getData();
            desc = "[" + file.getFileName() + "删除文件成功";
        } catch (Exception e) {
            desc = "[" + file.getFileName() + "]删除文件异常";
            logger.error(desc, e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return msg;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message insertFile(Message msg) throws BusinessException {
        logger.info("新增文件，{}", msg);
        String desc = "新增文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> insertFileResult = consumerFileService.insertFile(cityFile);
            file = insertFileResult.getData();
            desc = "[" + file.getFileName() + "]新增文件成功";
        } catch (Exception e) {
            desc = "[" + file.getFileName() + "]新增文件异常";
            logger.error(desc, e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return msg;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message getFileById(Message msg) throws BusinessException {
        logger.info("查询文件，{}", msg);
        String desc = "查询文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> fileResult = consumerFileService.getFileById(cityFile.getId());
            file = fileResult.getData();
            desc = "查询文件成功";
        } catch (Exception e) {
            desc = "查询文件异常";
            logger.error(desc, e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return msg;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message getFileList(Message msg) throws BusinessException {
        logger.info("查询文件列表，{}", msg);
        String desc = "查询文件列表失败";
        List<CityFile> fileList = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<List<CityFile>> listResult = consumerFileService.getFileList(cityFile);
            fileList = listResult.getData();
            desc = "查询文件列表成功";
        } catch (Exception e) {
            desc = "查询文件列表异常";
            logger.error(desc, e);
        }
        msg.getData().setData(fileList);
        msg.setDesc(desc);
        return msg;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Message updateFileById(Message msg) throws BusinessException {

        logger.info("修改文件，{}", msg);
        String desc = "修改文件失败";
        CityFile file = null;
        try {
            CityFile cityFile = DataUtils.getCityFileFromMsg(msg);
            Result<CityFile> fileResult = consumerFileService.updateFileById(cityFile);
            file = fileResult.getData();
            desc = "修改文件成功";
        } catch (Exception e) {
            desc = "修改文件异常";
            logger.error(desc, e);
        }
        msg.getData().setData(file);
        msg.setDesc(desc);
        return msg;
    }
}
