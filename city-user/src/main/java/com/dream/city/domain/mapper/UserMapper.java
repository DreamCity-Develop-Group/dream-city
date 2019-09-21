package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wvv
 */
@Repository
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int save (User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update (User user);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById (int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectById (int id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getUsers ();

    @Insert({"<script>" +
            "INSERT INTO  mt_message_template (" +
            "message_template_id,\n" +
            "message_template_head_id,\n" +
            "message_title,\n" +
            "message_content,\n" +
            "pushapp_type,\n" +
            "platform_hospital_id,\n" +
            "template_id_weixin,\n" +
            "template_weixin,\n" +
            "template_alipay,\n" +
            "template_id_zhifubao,\n" +
            "createdby,\n" +
            "createdon,\n" +
            "modifiedby,\n" +
            "modifiedon,\n" +
            "deletion_state)" +
            "values  " +
            "<foreach collection=\"mtMessageTemplateList\" item=\"item\" separator=\",\">" +
            "(#{item.messageTemplateId}, #{item.messageTemplateHeadId}, " +
            "#{item.messageTitle}, #{item.messageContent}, " +
            "#{item.pushAppType}, #{item.platformHospitalId}, " +
            "#{item.emplateIdWeixin}, #{item.templateWeixin}, " +
            "#{item.templateAlipay}, #{item.templateIdZhifubao}, " +
            "#{item.createdby}, #{item.createdon}, " +
            "#{item.modifiedby}, #{item.modifiedon}, " +
            "#{item.deletionState})" +
            "</foreach>" +
            "</script>"})
    void insert(@Param("mtMessageTemplateList") List<User> mtMessageTemplateList);

    @Select("<script>"
            + "SELECT "
            + "a.id as 'id',a.create_date as 'createDate',a.content as 'content',"
            + "a.parent_id as 'parentId',a.first_comment_id as 'firstCommentId',"
            + "b.id as 'fromUser.id',b.realname as 'fromUser.realname',b.avatar as 'fromUser.avatar',"
            + "c.id as 'toUser.id',c.realname as 'toUser.realname',c.avatar as 'toUser.avatar' "
            + "FROM t_demand_comment a "
            + "LEFT JOIN t_user b ON b.id = a.from_uid "
            + "LEFT JOIN t_user c ON c.id = a.to_uid "
            + "WHERE a.demand_id = #{demandId} "
            + "ORDER BY a.create_date ASC "
            + "<if test='startNo!=null and pageSize != null '>"
            + "LIMIT #{startNo},#{pageSize}"
            + "</if>"
            + "</script>")
    public List<User> listDemandComment(@Param("demandId") Long demandId, @Param("startNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}
