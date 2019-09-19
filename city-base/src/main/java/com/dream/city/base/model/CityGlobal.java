package com.dream.city.base.model;

public interface CityGlobal {

    enum ResultCode {

        success(200,"成功"),

        fail(404,"失败");

        // 成员变量
        private int status;
        private String name;
        private String message;

        ResultCode(int status, String name){
            this.name = name;
            this.status = status;
        }

        public String getCode() {
            return this.name();
        }
        public int getStatus() {
            return status;
        }
        public String getMessage() {
            return message;
        }
    }


    class Constant {

        public static String COMMON_ENCODING = "UTF-8";

        public static final String YES = "Y";
        public static final String NO = "N";

        public static final String LINK_TIMEOUT = "链接超时!";

        // 登录
        public static final String LOGIN_SUCCESS = "登录成功!";
        public static final String LOGIN_FAIL = "登录失败!";

        // 注册
        public static final String REG_SUCCESS = "注册成功!";
        public static final String REG_FAIL = "注册失败!";
        public static final String REG_USER_EXIT = "用户已存在!";
        public static final String REG_USER_NICK_EXIST = "该昵称已被使用!";

        // 用户
        public static final String USER_NOT_EXIT = "用户不存在!";
        public static final String USER_NAME_NULL = "用户名不能为空！";
        public static final String USER_PWD_NULL = "用户密码不能为空！";
        public static final String USER_PWD_ERROR = "用户密码不正确！";

        public static final String USER_VLCODE_NULL = "验证码不能为空！";
        public static final String USER_VLCODE_ERROR = "验证码不正确！";
        public static final String USER_VLCODE_TIMEOUT= "该验证码超时，请重新获取！";

        public static final String USER_OLD_PWD_ERROR = "旧密码不正确！";
        public static final String USER_CHANGE_PWD_SUCCESS = "修改密码成功！";
        public static final String USER_CHANGE_PWD_FAIL = "修改密码失败！";

        public static final String USER_CHANGE_TRADERPWD_SUCCESS = "修改交易密码成功！";
        public static final String USER_CHANGE_TRADERPWD_FAIL = "修改交易密码失败！";







    }


}
