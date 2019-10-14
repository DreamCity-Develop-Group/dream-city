package com.dream.city.base.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Wvv
 */
@Data
public class UserVo implements Serializable {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户状态
     */
    private Integer state;

    /**
     * 工号
     */
    private String no;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private Date loginDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private Integer delFlag;

}