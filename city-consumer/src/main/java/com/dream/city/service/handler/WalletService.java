package com.dream.city.service.handler;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.DepositCallbackLog;
import com.dream.city.base.model.vo.DepositParamVo;
import com.dream.city.base.model.vo.WithdrawFeedbackParamVo;
import com.dream.city.base.model.vo.WithdrawParamVo;
import com.dream.city.base.model.vo.WithdrawTaskRecord;

import java.util.List;

public interface WalletService {

    List<WithdrawTaskRecord> getWithdrawList(WithdrawParamVo dataVo);

    Result withDrawResultCallback(WithdrawFeedbackParamVo dataVo);

    Result depositCallback(DepositParamVo dataVo,DepositCallbackLog log);

    void addDepositCallbackLog(DepositCallbackLog dlog);

    DepositCallbackLog getDepositCallbackLog(String txId);
}
