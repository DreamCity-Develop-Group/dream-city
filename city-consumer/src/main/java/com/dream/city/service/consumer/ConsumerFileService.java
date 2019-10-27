package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "city-set")
@RequestMapping("/set/file")
public interface  ConsumerFileService {


    @RequestMapping("/deleteFileById/{id}")
    Result<Integer> deleteFileById(@PathVariable("id") Long id);

    @RequestMapping("/insertFile")
    Result<CityFile> insertFile(@RequestBody CityFile record);

    @RequestMapping("/getFileById/{id}")
    Result<CityFile> getFileById(@PathVariable("id") Long id);

    @RequestMapping("/getFileList")
    Result<List<CityFile>> getFileList(@RequestBody CityFile record);

    @RequestMapping("/updateFileById")
    Result<CityFile> updateFileById(@RequestBody CityFile record);



}
