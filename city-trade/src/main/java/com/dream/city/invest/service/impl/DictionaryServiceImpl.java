package com.dream.city.invest.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.entity.Dictionary;
import com.dream.city.base.model.mapper.DictionaryMapper;
import com.dream.city.invest.service.DictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @LcnTransaction
    @Transactional
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

    @LcnTransaction
    @Transactional
    @Override
    public int deleteById(Integer id) {
        Integer integer = dictionaryMapper.deleteByPrimaryKey(id);
        return integer==null?0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Dictionary getById(Integer id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public String getKeyByVal(String val) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByVal(val);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0).getKey();
        }
        return null;
    }

    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    @Override
    public String getValByKey(String key) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByKey(key);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0).getVal();
        }
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Dictionary getOneByKey(String key) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByKey(key);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0);
        }
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Dictionary getOneByVal(String val) {
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryByVal(val);
        if (!CollectionUtils.isEmpty(dictionaryList)){
            return dictionaryList.get(0);
        }
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<Dictionary> getListByKey(String key) {
        return dictionaryMapper.getDictionaryByKey(key);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<Dictionary> getListByVal(String val) {
        return dictionaryMapper.getDictionaryByVal(val);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<Dictionary> getListByName(String name) {
        return dictionaryMapper.getDictionaryByName(name);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PageInfo<Dictionary> getDictionaryList(Page pageReq, Dictionary record) {
        PageHelper.startPage(pageReq.getPageNum(),pageReq.getPageSize());
        List<Dictionary> dictionaryList = dictionaryMapper.getDictionaryList(record);
        return new PageInfo<>(dictionaryList);
    }
}
