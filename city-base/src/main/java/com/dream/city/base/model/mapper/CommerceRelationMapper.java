package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CommerceRelation;
import com.dream.city.base.model.entity.RelationTree;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wvv
 */
@Mapper
public interface CommerceRelationMapper {
    int deleteByPrimaryKey(Integer treeId);

    int insert(CommerceRelation record);

    int insertSelective(CommerceRelation record);

    RelationTree selectByPrimaryKey(Integer treeId);

    int updateByPrimaryKeySelective(CommerceRelation record);

    int updateByPrimaryKey(CommerceRelation record);

    RelationTree getByPlayer(@Param("treePlayerId") String treePlayerId);

    @Results(id = "treeBaseMap", value = {
            @Result(property = "treeId", column = "tree_id", id = true),
            @Result(property = "treeParentId", column = "tree_parent_id"),
            @Result(property = "treePlayerId", column = "tree_player_id"),
            @Result(property = "treeRelation", column = "tree_relation"),
            @Result(property = "sendAuto", column = "send_auto"),
            @Result(property = "treeLevel", column = "tree_level"),
            @Result(property = "treeChilds", column = "tree_childs"),
            @Result(property = "treeGrandson", column = "tree_grandson"),
            @Result(property = "createTime", column = "create_time")

    })

    @Select({"select * from `city_commerce_relation` where  tree_player_id = #{sonId} limit 0,1"})
    CommerceRelation getCommerceRelationBySonId(@Param("sonId") String sonId);

}