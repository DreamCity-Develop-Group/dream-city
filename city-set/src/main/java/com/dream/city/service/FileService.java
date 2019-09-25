package com.dream.city.service;

import com.dream.city.base.model.entity.CityFile;

import java.util.List;

public interface FileService {

    int deleteFileById(Long id);

    CityFile insertFile(CityFile record);

    CityFile getFileById(Long id);

    List<CityFile> getFileList(CityFile record);

    CityFile updateFileById(CityFile record);

}
