package com.dream.city.domian;

import com.dream.city.base.model.entity.RelationTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

@Mapper
public interface TreeMapper {

    @Select("select " +
            "tree_id treeId,tree_parent_id treeParentId, " +
            "tree_player_id treePlayerId," +
            "tree_relation treeRelation," +
            "send_auto sendAuto," +
            "tree_level treeLevel," +
            "create_time createTime " +
            "from city_tree where 1=1 and tree_player_id=#{parentId}")
    RelationTree getTreeByPlayerId(String playerId);
}
