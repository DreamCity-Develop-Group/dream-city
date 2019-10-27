package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

public interface EarnService {
    Message extract(Message msg) throws BusinessException;


    /**
     * 查询提现规则
     *
     * @param msg
     * @return
     */
    Message getEarning(Message msg) throws BusinessException;

    /**
     * 查询提现规则列表
     *
     * @param msg
     * @return
     */
    Message getEarningList(Message msg) throws BusinessException;


    /**
     * 删除提现规则
     *
     * @param msg
     * @return
     */
    Message deleteEarningById(Message msg) throws BusinessException;


    /**
     * 新增提现规则
     *
     * @param msg
     * @return
     */
    Message insertEarning(Message msg) throws BusinessException;

    /**
     * 更新提现规则
     *
     * @param msg
     * @return
     */
    Message updateEarningById(Message msg) throws BusinessException;
}
