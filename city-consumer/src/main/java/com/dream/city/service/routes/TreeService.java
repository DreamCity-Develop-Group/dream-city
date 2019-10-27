package com.dream.city.service.routes;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

public interface TreeService {

    Message treeAdd(Message msg)  throws BusinessException;

    /**
     * 添加经营许可
     *
     * @param msg
     * @return
     */
    Message Join(Message msg)  throws BusinessException;

    /**
     * 获取商会成员
     *
     * @param msg
     * @return
     */
    Message getMembers(Message msg)  throws BusinessException;

    /**
     * 获取订单
     *
     * @param msg
     * @return
     */
    Message getSalesOrder(Message msg)  throws BusinessException;

    /**
     * 设置自动发货，并备货
     *
     * @param msg
     * @return
     */
    Message setAutoSend(Message msg)  throws BusinessException;

    /**
     * 订单发货
     *
     * @param msg
     * @return
     */
    Message sendOrder(Message msg) throws BusinessException;


    /**
     * 订单创建
     *
     * @param msg
     * @return
     */
    Message createOrder(Message msg) throws BusinessException;


    /**
     * 验证订单支付密码
     *
     * @param msg
     * @return
     */
    Message checkOrderPass(Message msg) throws BusinessException;
}
