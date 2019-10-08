package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典
 * @author
 */
@Mapper
public interface DictionaryMapper {


    Integer insertSelective(Dictionary record);

    Integer updateByPrimaryKeySelective(Dictionary record);

    Integer deleteByPrimaryKey(Integer id);

    Dictionary selectByPrimaryKey(Integer id);

    List<Dictionary> getDictionaryByKey(String key);

    List<Dictionary> getDictionaryByVal(String val);

    List<Dictionary> getDictionaryByName(String name);

    List<Dictionary> getDictionaryList(Dictionary record);

}