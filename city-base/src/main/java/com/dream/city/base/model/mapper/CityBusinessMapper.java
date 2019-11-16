package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityBusiness;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wvv
 */
@Mapper
public interface CityBusinessMapper {

    @Results(id = "businessBaseMap", value = {
            @Result(property = "businessId", column = "business_id", id = true),
            @Result(property = "businessParentId", column = "business_parent_id"),
            @Result(property = "businessPlayerId", column = "business_player_id"),
            @Result(property = "CityBusiness", column = "business_relation"),
            @Result(property = "businessEnabled", column = "business_enabled"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `city_business` where 1=1 and business_id = #{businessId}"})
    CityBusiness getCityBusiness(@Param("businessId") String businessId);


    @Select("select * from city_business where 1=1")
    List<CityBusiness> getCity();

    @Select("select * from city_business where 1=1 and business_parent_id = #{pid} and business_player_id=#{cid}")
    CityBusiness get(@Param("pid") String pid, @Param("cid") String cid);

    @Insert({"insert into `city_business`(" +
            " business_id,business_parent_id," +
            " business_player_id, business_relation,business_enabled," +
            " create_time) " +
            " values (#{businessId},#{businessParentId}, #{businessPlayerId}, " +
            " #{businessRelation},#{businessEnabled}," +
            " #{createTime})"})
        //@Options(useGeneratedKeys = true, keyProperty = "business_id")
    void savebusiness(CityBusiness business);

    @Select("select * from city_business where 1=1 limit 0,10")
    List<CityBusiness> getbusinesss();

    @Update(" update city_business set " +
            " business_parent_id=#{businessParentId}," +
            " business_player_id=#{businessPlayerId}," +
            " business_relation=#{businessRelation}," +
            " business_enabled=#{businessEnabled}, " +
            " where 1=1 and business_id = #{businessId}")
    void updatebusiness(CityBusiness business);



    @Select("select " +
            " business_id businessId," +
            " business_parent_id businessParentId, " +
            " business_player_id businessPlayerId," +
            " business_relation CityBusiness," +
            " send_auto sendAuto," +
            " business_enabled businessEnabled," +
            " create_time createTime " +
            " update_time updateTime " +
            " from city_business where 1=1 and business_relation like  #{relation}")
    List<CityBusiness> selectByRelation(@Param("relation") String relation);


    @Select("select * from city_business where 1=1 and business_parent_id=#{parentId}")
    List<CityBusiness> getChilds(@Param("parentId") String parentId);

    @Select("select " +
            " business_id businessId," +
            " business_parent_id businessParentId, " +
            " business_player_id businessPlayerId," +
            " business_relation businessRelation," +
            " business_enabled businessEnabled," +
            " create_time createTime, " +
            " update_time updateTime " +
            " from city_business where 1=1 and business_player_id=#{playerId}  ORDER BY create_time DESC limit 1")
    CityBusiness getbusinessByPlayerId(@Param("playerId") String playerId);

    @Select("select " +
            " business_id businessId," +
            " business_parent_id businessParentId, " +
            " business_player_id businessPlayerId," +
            " business_relation businessRelation," +
            " business_enabled businessEnabled," +
            " create_time createTime, " +
            " update_time updateTime " +
            " from city_business where 1=1 and business_player_id=#{playerId} and business_enabled='1' limit 1 ")
    CityBusiness getEnabledCityBusiness(@Param("playerId")String playerId);

    @Update(" update city_business set business_enabled='1' where business_id=#{businessId} and business_enabled='0'")
    int updateBusinessEnable(@Param("businessId") Integer businessId);
}