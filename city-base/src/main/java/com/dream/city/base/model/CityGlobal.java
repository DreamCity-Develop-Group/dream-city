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

        // 登录
        public static final String LOGIN_SUCCESS = "登录成功!";
        public static final String LOGIN_FAIL = "登录失败!";
        public static final String LOGIN_TIMEOUT = "登录超时!";

        // 用户
        public static final String USER_NOT_EXIT = "用户不存在!";
        public static final String USER_NAME_NULL = "用户名不能为空！";
        public static final String USER_PWD_NULL = "用户密码不能为空！";



    }


}
