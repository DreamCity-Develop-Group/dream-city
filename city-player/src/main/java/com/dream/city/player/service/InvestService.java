package com.dream.city.player.service;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;

import java.util.List;

/**
 * 物业/投资项
 */
public interface InvestService {

    /**
     * 新建物业
     * @param record
     * @return
     */
    int insertInvest(CityInvest record);


    /**
     * 查询物业
     * @param record
     * @return
     */
    CityInvest getInvest(CityInvest record);

    /**
     * 更新物业
     * @param record
     * @return
     */
    int updateInvest(CityInvest record);

    /**
     * 物业列表
     * @param record
     * @return
     */
    List<CityInvest> getInvestLsit(CityInvest record);




    /**
     * 投资物业
     * @param record
     * @return
     */
    int insertInvestOrder(InvestOrder record);

    /**
     * 投资订单作废
     * @param record
     * @return
     */
    int investOrderInvalid(InvestOrder record);

    /**
     * 投资订单取消
     * @param record
     * @return
     */
    int investOrderCancel(InvestOrder record);

    /**
     * 查询投资订单
     * @param record
     * @return
     */
    InvestOrder getInvestOrder(InvestOrder record);

    /**
     * 投资列表
     * @param record
     * @return
     */
    List<InvestOrder> getInvestOrders(InvestOrder record);



}
