package com.dream.city.base.service.impl;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.entity.Dictionary;
import com.dream.city.base.model.mapper.DictionaryMapper;
import com.dream.city.base.service.DictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;



    @Override
    public int insert(Dictionary record) {
        Integer integer = dictionaryMapper.insertSelective(record);
        return integer==null?0:integer;
    }

    @Override
    public int updateById(Dictionary record) {
        Integer integer = dictionaryMapper.updateByPrimaryKeySelective(record);
        return integer==null?0:integer;
    }

    @Override
    public int deleteById(Integer id) {
        Integer integer = dictionaryMapper.deleteByPrimaryKey(id);
        return integer==null?0:integer;
    }

    @Override
    public Dictionary getById(Integer id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }

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

    @Override
    public PageInfo<Dictionary> getDictionaryList(Page pageReq, Dictionary record) {
        PageHelper.startPage(pageReq.getPageNum(),pageReq.getPageSize());
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryList(record);
        return new PageInfo<>(dictionaryList);
    }
}
