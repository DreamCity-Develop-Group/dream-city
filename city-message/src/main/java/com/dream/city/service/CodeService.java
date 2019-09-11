package com.dream.city.service;

/**
 * @author Wvv
 */
public interface CodeService {

    boolean insertCode(String phone,String code);

    boolean valid(String phone,String code);
}
