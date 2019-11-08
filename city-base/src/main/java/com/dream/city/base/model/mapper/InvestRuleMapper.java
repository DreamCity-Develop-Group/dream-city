package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.req.RuleReq;
import com.dream.city.base.model.resp.RuleResp;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Mapper
public interface InvestRuleMapper {

    int deleteByPrimaryKey(Integer ruleId);

    int insert(InvestRule record);

    int insertSelective(InvestRule record);

    InvestRule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKey(InvestRule record);

    List<RuleResp> getInvestRuleList(RuleReq record);

    List<InvestRule> getRuleFlagList(InvestRule record);

    Integer updateByPrimaryKeySelective(InvestRule record);

    @Results(id = "InvestRuleBaseMap", value = {
            @Result(property = "ruleId", column = "rule_id", id = true),
            @Result(property = "ruleFlag", column = "rule_flag"),
            @Result(property = "ruleOpt", column = "rule_opt"),
            @Result(property = "ruleOptPre", column = "rule_opt_pre"),
            @Result(property = "ruleName", column = "rule_name"),
            @Result(property = "ruleDesc", column = "rule_desc"),
            @Result(property = "ruleItem", column = "rule_item"),
            @Result(property = "ruleRate", column = "rule_rate"),
            @Result(property = "ruleRatePre", column = "rule_rate_pre"),
            @Result(property = "ruleLevel", column = "rule_level"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `invest_rule` where 1=1 and rule_id = #{ruleId}"})
    InvestRule getRuleById(String ruleId);

    @Select("select * from `invest_rule` where 1=1 and  rule_item = #{itemId}")
    @ResultMap("InvestRuleBaseMap")
    List<InvestRule> getRulesByItem(Integer itemId);

    /**
     * 获取单个投资
     *
     */
    @Select({"select * from `invest_rule` where 1=1 and in_id = #{investId}"})
    InvestRule getCityInvest(Integer investId);

    /**
     * 获取所有投资项列表
     * @return
     */
    @Select("select * from `invest_rule` where 1=1 ")
    List<InvestRule> getAllCityInvests();

    @Select({
            "<script>",
            "select",
            "*",
            "from `invest_rule`",
            "where 1=1 and and Rule_id = #{RuleId} and rule_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    List<InvestRule> getSuccessCityInvestnsByRuleId(@Param("RuleId") String RuleId, @Param("states") int[] states);


    /**
     * 指更新状态
     *
     *
     * @param ruleList
     */
    @Update({"<script>" +
            "<foreach collection=\"ruleList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `invest_rule`" +
            " SET rule_state = #{item.ruleState, jdbcType=TINYINT}, " +
            "  WHERE " +
            "  AND rule_id = #{item.ruleId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void setRuleState(@Param("ruleList") List<InvestRule> ruleList);

    //@Select("select * from `invest_rule` where 1=1 and rule_invest_id = #{investId} and rule_state=#{state}")
    @Select("select * from `invest_rule` where 1=1 and rule_id = #{investId} ")
    @ResultMap("InvestRuleBaseMap")
    List<InvestRule> getInvestRuleByKey(Integer key);

    @Select("select * from `invest_rule` where 1=1 ")
    @ResultMap("InvestRuleBaseMap")
    List<InvestRule> selectRules();


    @Select("select * from `invest_rule` where 1=1 and rule_item=#{itemId} ")
    @ResultMap("InvestRuleBaseMap")
    List<InvestRule> getInvestRuleByItem(Integer itemId);
}