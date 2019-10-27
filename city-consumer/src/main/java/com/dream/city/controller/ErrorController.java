package com.dream.city.controller;


import com.dream.city.base.exception.BusinessException;
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
    public Map<String, String> customerExceptionHandler(BusinessException ex) {
        Map<String, String> res = new HashMap<>();
        ex.printStackTrace();
        res.put("errCode", ex.getCode());
        res.put("errMsg", ex.getMessage());
        return res;
    }

    @ExceptionHandler(SQLException.class)
    public Map<String, String> sqlExceptionHandler(SQLException ex) {
        Map<String, String> res = new HashMap<>();
        ex.printStackTrace();
        res.put("errorCode", "3306");
        res.put("errorMsg", ex.getMessage());
        return res;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> exceptionHandler(Exception ex) {
        Map<String, String> res = new HashMap<>();
        ex.printStackTrace();
        res.put("errorCode", "3000");
        res.put("errorMsg", ex.getMessage());
        return res;
    }
}
