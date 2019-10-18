package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<Dictionary> getDictionaryByKey(@Param("key") String key);

    List<Dictionary> getDictionaryByVal(@Param("val")String val);

    List<Dictionary> getDictionaryByName(@Param("name")String name);

    List<Dictionary> getDictionaryList(Dictionary record);

}