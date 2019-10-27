package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;

import java.util.List;

public interface InvestService {
     Message playerInvest(Message msg)throws BusinessException ;


    /**
     * 投资
     * @param msg
     * @return
     */
    /* Message playerInvesting(Message msg)*/


    /**
     * 根据投资id查询订单
     * @param msg
     * @return
     */
    
     Message getInvest(Message msg) throws BusinessException;


    /**
     * 投资列表
     * @param msg
     * @return
     */
    
     Message getInvestList(Message msg) throws BusinessException;


    /**
     * 新建物业
     * @param invest
     * @return
     */
    Result<Integer> insertInvest(CityInvest invest);


    /**
     * 查询物业
     * @param invest
     * @return
     */
    Result<CityInvest> getInvestByIdOrName(CityInvest invest);

    /**
     * 更新物业
     * @param invest
     * @return
     */
    Result<Integer> updateInvest(CityInvest invest);

    /**
     * 物业列表
     * @param invest
     * @return
     */
    Result<List<InvestResp>> getInvestLsit(CityInvestReq invest);
}
