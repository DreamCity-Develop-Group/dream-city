package com.dream.city.util;

import com.dream.city.domain.ApiReturnObject;
import com.dream.city.domain.ApiSendObject;

/**
 * @author Wvv
 */
public class ApiUtil {
    public static ApiReturnObject error(String msg){
        return new ApiReturnObject(msg);
    }

    public static ApiReturnObject success(String code){
        return new ApiReturnObject(code);
    }

    public static ApiSendObject pack(String msg){
        return new ApiSendObject(msg);
    }
}
