package com.dream.city.service;

import com.dream.city.base.model.entity.EarnFalldownLog;
import com.dream.city.base.model.entity.Player;
import com.mchange.v2.log.FallbackMLog;
import org.springframework.stereotype.Repository;

/**
 * @author Wvv
 */

public interface FallDownLogService {
    /**
     *
     * @param fallDownLog
     * @return
     */
    boolean save(EarnFalldownLog fallDownLog);

    /**
     * 新增falldownLog
     * @param fallDownLog
     * @return
     */

    boolean createFalldownLog(EarnFalldownLog fallDownLog);

}
