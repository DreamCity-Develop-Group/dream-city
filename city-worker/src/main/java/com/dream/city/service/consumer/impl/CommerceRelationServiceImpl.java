package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.CommerceRelation;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.mapper.CommerceRelationMapper;
import com.dream.city.service.CommerceRelationService;
import com.dream.city.service.RelationTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Wvv
 */

@Service
public class CommerceRelationServiceImpl implements CommerceRelationService {
    @Autowired
    private CommerceRelationMapper commerceRelationMapper;



    @LcnTransaction
    @Transactional
    @Override
    public CommerceRelation getCommerceRelationBySonId(String sonId) {
        return commerceRelationMapper.getCommerceRelationBySonId(sonId);
    }
}
