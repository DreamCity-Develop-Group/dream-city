package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RelationTree;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wvv
 */
@Mapper
public interface RelationTreeMapper {
    int deleteByPrimaryKey(Integer treeId);

    int insert(RelationTree record);

    int insertSelective(RelationTree record);

    RelationTree selectByPrimaryKey(Integer treeId);

    int updateByPrimaryKeySelective(RelationTree record);

    int updateByPrimaryKey(RelationTree record);

    RelationTree getByPlayer(String treePlayerId);

    @Results(id = "treeBaseMap", value = {
            @Result(property = "treeId", column = "tree_id", id = true),
            @Result(property = "treeParentId", column = "tree_parent_id"),
            @Result(property = "treePlayerId", column = "tree_player_id"),
            @Result(property = "treeRelation", column = "tree_relation"),
            @Result(property = "sendAuto", column = "send_auto"),
            @Result(property = "treeLevel", column = "tree_level"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `city_tree` where 1=1 and tree_id = #{treeId}"})
    RelationTree getRuleById(String treeId);


    @Select("select * from city_tree where 1=1")
    List<RelationTree> getCity();

    @Select("select * from city_tree where 1=1 and tree_parent_id = #{pid} and tree_player_id=#{cid}")
    RelationTree get(String pid,String cid);

    @Insert({"insert into `city_tree`(tree_id,tree_parent_id, tree_player_id, tree_relation,send_auto,tree_level,create_time) " +
            "values (#{treeId},#{treeParentId}, #{treePlayerId}, #{treeRelation},#{sendAuto},#{treeLevel},#{createTime})"})
    //@Options(useGeneratedKeys = true, keyProperty = "tree_id")
    void  saveTree(RelationTree tree);

    @Select("select * from city_tree where 1=1 limit 0,10")
    List<RelationTree> getTrees();

    @Update(" update city_tree set tree_parent_id=#{treeParentId},tree_player_id=#{treePlayerId},tree_relation=#{treeRelation},send_auto=#{sendAuto},tree_level=#{treeLevel} where tree_id = #{treeId}")
    void updateTree(RelationTree tree);

    /**
     * 根据关系取玩家
     *
     * @param relation
     * @return
     */
    @Select("select " +
            "tree_id treeId,tree_parent_id treeParentId, " +
            "tree_player_id treePlayerId," +
            "tree_relation treeRelation," +
            "send_auto sendAuto," +
            "tree_level treeLevel," +
            "create_time createTime " +
            "from city_tree where 1=1 and tree_relation=#{relation} limit 1 ")
    RelationTree getTreeByRef(String relation);

    @Select("select " +
            "tree_id treeId," +
            "tree_parent_id treeParentId, " +
            "tree_player_id treePlayerId," +
            "tree_relation treeRelation," +
            "send_auto sendAuto," +
            "tree_level treeLevel," +
            "create_time createTime " +
            "from city_tree where 1=1 and tree_relation like  #{relation}")
    List<RelationTree> selectByRelation(String relation);


    @Select("select * from city_tree where 1=1 and tree_parent_id=#{parentId}")
    List<RelationTree> getChilds(String parentId);

    @Select("select " +
            "tree_id treeId," +
            "tree_parent_id treeParentId, " +
            "tree_player_id treePlayerId," +
            "tree_relation treeRelation," +
            "send_auto sendAuto," +
            "tree_level treeLevel," +
            "create_time createTime " +
            "from city_tree where 1=1 and tree_player_id=#{playerId}")
    //@ResultMap("treeBaseMap")
    RelationTree getTreeByPlayerId(String playerId);
}