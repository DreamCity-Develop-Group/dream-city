package com.dream.city.base.model;

/**
 * 操作结果
 * @param <T>
 */
public class Result<T> {

    /**
     * 操作是否成功
     */
    private boolean success;

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;


    public Result() {
    }


    public Result(String msg, int code) {
        super();
        this.msg = msg;
        this.code = code;
    }

    public Result(int code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public Result(String msg, int code, T data) {
        super();
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Result(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public Result(boolean success, String msg, int code) {
        super();
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public Result(boolean success, String msg, int code, T data) {
        super();
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
