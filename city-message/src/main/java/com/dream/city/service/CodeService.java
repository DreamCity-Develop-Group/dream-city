package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.AuthCode;

/**
 * @author Wvv
 */
public interface CodeService {

    boolean insertCode(String phone,String code);

    boolean valid(String phone,String code);

    Result updateCodeState(AuthCode code);

    AuthCode getAuthCodeByPhone(String phone);
}
