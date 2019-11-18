package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.entity.CommerceRelation;
import com.dream.city.base.model.mapper.CityBusinessMapper;
import com.dream.city.base.model.mapper.CommerceRelationMapper;
import com.dream.city.service.BusinessService;
import com.dream.city.service.CommerceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wvv
 */

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private CityBusinessMapper cityBusinessMapper;

}
