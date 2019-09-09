package com.dream.city.service.impl;

import com.dream.city.service.CodeService;
import org.springframework.stereotype.Service;

/**
 * @author Wvv
 */
@Service
public class CodeServiceImpl implements CodeService {
    @Override
    public boolean valid(String phone, String code) {

        return true;
    }
}
