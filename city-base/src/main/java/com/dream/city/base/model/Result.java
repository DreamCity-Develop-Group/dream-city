package com.dream.city.base.model;

import com.dream.city.base.model.enu.ReturnStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param <T>
 * @author wvv
 * <p>
 * 操作结果
 */
@ApiModel(description = "返回响应数据")
@Data
public class Result<T> {

    /**
     * 操作是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private  boolean success;

    /**
     * 返回码
     */
    @ApiModelProperty(value = "状态码")
    private int code;

    /**
     * 返回消息
     */
    @ApiModelProperty(value = "消息描述")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "业务数据")
    private T data;


    public Result() {
    }


    public Result(String msg, int code) {
        super();
        this.msg = msg;
        this.code = code;
        if (code == CityGlobal.ResultCode.success.getStatus()) {
            this.success = Boolean.TRUE;
        }
    }

    public Result(int code, T data) {
        super();
        this.code = code;
        this.data = data;
        if (code == CityGlobal.ResultCode.success.getStatus()) {
            this.success = Boolean.TRUE;
        }
    }

    public Result(boolean success, String msg, T data) {
        super();
        this.success = success;
        this.msg = msg;
        this.data = data;
        if (success) {
            this.code = CityGlobal.ResultCode.success.getStatus();
        }
    }

    public Result(boolean success, String msg, int code) {
        super();
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = null;
    }

    public Result(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
        if (success) {
            this.code = CityGlobal.ResultCode.success.getStatus();
        }
    }

    public Result(boolean success, T data) {
        super();
        this.success = success;
        this.data = data;
        if (success) {
            this.code = CityGlobal.ResultCode.success.getStatus();
        }
    }

    public Result(String msg, int code, T data) {
        super();
        this.msg = msg;
        this.code = code;
        this.data = data;
        if (code == CityGlobal.ResultCode.success.getStatus()) {
            this.success = Boolean.TRUE;
        }
    }

    public Result(boolean success, String msg, int code, T data) {
        super();
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Result(boolean success){
        super();
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public static Result result(boolean success, String msg, int code, Object data) {
        return new Result(success, msg, code, data);
    }

    public static Result result(boolean success, String msg, int code) {
        return new Result(success, msg, code);
    }

    public static Result result(boolean success, String msg) {
        return new Result(success, msg);
    }

    public static Result result(boolean success, int code) {
        return new Result(success, code);
    }

    public static Result result(boolean success, Object data) {
        return new Result(success, data);
    }

    public static Result result(String msg, int code, Object data) {
        return new Result(msg, code, data);
    }

    public static Result result(boolean success) {
        return new Result(success);
    }


    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +py
                '}';
    }

    public static void main(String[] args) {
        Result result = Result.result(true,"tesst", ReturnStatus.SUCCESS.getStatus());
        if (result.getSuccess()){
            System.out.println("true");
        }
        assert result.getSuccess();
    }
}



