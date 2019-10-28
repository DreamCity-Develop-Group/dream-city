package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.CityFile;
import com.dream.city.base.model.mapper.CityFileMapper;
import com.dream.city.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileServiceImpl  implements FileService {

    @Autowired
    CityFileMapper fileMapper;

    @LcnTransaction
    @Transactional
    @Override
    public int deleteFileById(Long id) throws BusinessException {
        return fileMapper.deleteByPrimaryKey(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public CityFile insertFile(CityFile record) throws BusinessException {
        Integer integer = fileMapper.insertSelective(record);
        if (integer == null || integer < 1){
            return null;
        }
        return record;
    }

    @LcnTransaction
    @Transactional
    @Override
    public CityFile getFileById(Long id) throws BusinessException {
        return fileMapper.getFileById(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<CityFile> getFileList(CityFile record) throws BusinessException {
        return fileMapper.getFileList(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public CityFile updateFileById(CityFile record)  throws BusinessException{
        Integer integer = fileMapper.updateByPrimaryKeySelective(record);
        if (integer == null || integer < 1){
            return null;
        }
        return record;
    }
}
