package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.EarnFalldownLog;

import com.dream.city.base.model.mapper.EarnFalldownLogMapper;
import com.dream.city.service.FallDownLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class FallDownLogServiceImpl implements FallDownLogService {

    @Autowired
    private  FallDownLogService fallDownLogService;
    @Autowired
    private EarnFalldownLogMapper earnFalldownLogMapper;

    @LcnTransaction
    @Transactional
    @Override
    public boolean save(EarnFalldownLog fallDownLog)  throws BusinessException {

        Integer integer = earnFalldownLogMapper.insertSelective(fallDownLog);
        //return (integer != null && integer > 0) ? Boolean.TRUE : Boolean.FALSE;
        return true;
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean createFalldownLog(EarnFalldownLog fallDownLog) {
        //boolean save = FallDownLogService.save(fallDownLog);
        return false;
    }
}
