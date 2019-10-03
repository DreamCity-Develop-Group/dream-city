package com.dream.city.service.impl;

import com.dream.city.base.model.entity.CityFile;
import com.dream.city.base.model.mapper.CityFileMapper;
import com.dream.city.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl  implements FileService {

    @Autowired
    CityFileMapper fileMapper;

    @Override
    public int deleteFileById(Long id) {
        return fileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CityFile insertFile(CityFile record) {
        Integer integer = fileMapper.insertSelective(record);
        if (integer == null || integer < 1){
            return null;
        }
        return record;
    }

    @Override
    public CityFile getFileById(Long id) {
        return fileMapper.getFileById(id);
    }

    @Override
    public List<CityFile> getFileList(CityFile record) {
        return fileMapper.getFileList(record);
    }

    @Override
    public CityFile updateFileById(CityFile record) {
        Integer integer = fileMapper.updateByPrimaryKeySelective(record);
        if (integer == null || integer < 1){
            return null;
        }
        return record;
    }
}
