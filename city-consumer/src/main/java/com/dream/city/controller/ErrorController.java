package com.dream.city.controller;


import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.enu.ReturnStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class ErrorController {
    
    @ExceptionHandler(BusinessException.class)
    public Result customerExceptionHandler(BusinessException ex) {
        Map<String, String> res = new HashMap<>();
        ex.printStackTrace();
        res.put("errCode", ex.getCode());
        res.put("errMsg", ex.getMessage());
        return Result.result(false,"发生错误", ReturnStatus.ERROR.getStatus(),res);
    }

    @ExceptionHandler(SQLException.class)
    public Result sqlExceptionHandler(SQLException ex) {
        Map<String, String> res = new HashMap<>();
        ex.printStackTrace();
        res.put("errorCode", "3306");
        res.put("errorMsg", ex.getMessage());
        return Result.result(false,"发生错误", ReturnStatus.ERROR.getStatus(),res);
    }

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception ex) {
        Map<String, String> res = new HashMap<>();
        ex.printStackTrace();
        res.put("errorCode", "3000");
        res.put("errorMsg", ex.getMessage());

        return Result.result(false,"发生错误", ReturnStatus.ERROR.getStatus(),res);
    }
}
