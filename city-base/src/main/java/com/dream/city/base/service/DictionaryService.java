package com.dream.city.base.service;

import com.dream.city.base.model.entity.Dictionary;

import java.util.List;

public interface DictionaryService {



    String getKeyByVal(String val);

    String getValByKey(String key);

    Dictionary getOneByKey(String key);

    Dictionary getOneByVal(String val);

    List<Dictionary> getListByKey(String key);

    List<Dictionary> getListByVal(String val);

    List<Dictionary> getListByName(String name);

}
