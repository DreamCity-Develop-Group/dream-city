package com.dream.city.base.model.enu;

/**
 * 审核状态(待审核wait，已经审核，处理中verifying，pass审核通过并完成，notpass审核不通过)
 */
public enum VerifyStatus {
    //TODO 新建为：待审核状态，审核通过为 审核中
    WAIT("WAIT","待审核"),
    PASS("PASS","审核通过"),//表示已经完成，需要提交到钱包处理逻辑
    NOTPASS("NOTPASS","审核不通过"),
    DOING("DOING","已经提交钱包处理"),//表示已经完成，不需要提交到钱包处理逻辑
    FAILED("FAILED","到账失败"),
    SUCCESS("SUCCESS","到账成功");

    private String code;
    private String desc;

    VerifyStatus(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
