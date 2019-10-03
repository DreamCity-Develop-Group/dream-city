package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityFileMapper {


    Integer deleteByPrimaryKey(Long id);

    Integer insertSelective(CityFile record);

    CityFile getFileById(Long id);

    List<CityFile> getFileList(CityFile record);

    Integer updateByPrimaryKeySelective(CityFile record);

}