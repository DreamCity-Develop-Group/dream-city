package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.RelationTree;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wvv
 */
@Repository
public interface TreeMapper {

    @ResultMap("TreeBaseResultMap")
    @Select("select * from city_tree where 1=1 and id = #{id}")
    RelationTree getTreeById(Integer id);

    @ResultMap("TreeBaseResultMap")
    @Select("select * from city_tree where 1=1 and tree_parent_id = #{pid} and tree_player_id=#{cid}")
    RelationTree get(String pid,String cid);

    @Insert({"insert into `city_tree`(tree_parent_id, tree_player_id, tree_relation) values (#{parentId}, #{playerId}, #{relation})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void  saveTree(RelationTree tree);

    @Insert("insert city_tree set tree_parent_id=#{parent},tree_player_id=#{child},tree_relation=#{relation}")
    void saveTreeMap(Integer parent,Integer child,String relation);

    @Select("select * from city_tree where 1=1 limit 0,10")
    @ResultMap("TreeBaseResultMap")
    List<RelationTree> getTrees();

    @Update(" update city_tree set name=#{name} where id = #{id}")
    void updateTree(String name,Integer id);
    @Delete("delete from city_tree where 1=1 and id = #{id}")
    void deleteTree(Integer id);


    @Select("select * from city_tree where 1=1 and tree_parent_id = #{parentId} limit 1")
    @ResultMap("TreeBaseResultMap")
    List<RelationTree> getByParent(String parentId);

    @Select("select * from city_tree where 1=1 and tree_player_id=#{playerId} limit 1 ")
    @ResultMap("TreeBaseResultMap")
    RelationTree getByPlayer(String playerId);

    @Select("select * from city_tree where 1=1 and tree_relation like  #{relation}")
    @ResultMap("TreeBaseResultMap")
    List<RelationTree> selectByRelation(String relation);





}
