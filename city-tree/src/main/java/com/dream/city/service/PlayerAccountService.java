package com.dream.city.service;


import com.dream.city.base.model.Result;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
public interface PlayerAccountService {
    BigDecimal getPlayerAccountUSDTAvailble(String playerId);
    BigDecimal getPlayerAccountMTAvailble(String playerId);

    Result checkPayPass(String playerId, String payPass);
}
