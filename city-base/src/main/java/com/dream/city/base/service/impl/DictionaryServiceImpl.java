package com.dream.city.base.service.impl;

import com.dream.city.base.model.entity.Dictionary;
import com.dream.city.base.model.mapper.DictionaryMapper;
import com.dream.city.base.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;



    @Override
    public String getKeyByVal(String val) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByVal(val);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0).getKey();
        }
        return null;
    }

    @Override
    public String getValByKey(String key) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByKey(key);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0).getVal();
        }
        return null;
    }

    @Override
    public Dictionary getOneByKey(String key) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByKey(key);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0);
        }
        return null;
    }
    @Override
    public Dictionary getOneByVal(String val) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByVal(val);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0);
        }
        return null;
    }

    @Override
    public List<Dictionary> getListByKey(String key) {
        return dictionaryMapper.getDictionaryByKey(key);
    }

    @Override
    public List<Dictionary> getListByVal(String val) {
        return dictionaryMapper.getDictionaryByVal(val);
    }

    @Override
    public List<Dictionary> getListByName(String name) {
        return dictionaryMapper.getDictionaryByName(name);
    }
}
