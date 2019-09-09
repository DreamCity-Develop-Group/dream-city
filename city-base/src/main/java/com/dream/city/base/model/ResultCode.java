package com.dream.city.base.model;

public enum ResultCode {

    success("成功",200),

    fail("失败",404);

    // 成员变量
    private String name;
    private int index;

    ResultCode(String name,int index){
        this.name = name;
        this.index = index;
    }



}

