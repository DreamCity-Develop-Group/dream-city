package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;

public interface TestLcnService {

    Result setPlayerTree(String playerId,String invite,String pass) throws BusinessException;
}
