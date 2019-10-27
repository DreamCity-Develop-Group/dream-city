package com.dream.city.service.routes;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;

public interface TestLcnService {

    Result setPlayerTree(String playerId,String invite,String pass) throws BusinessException;
}
