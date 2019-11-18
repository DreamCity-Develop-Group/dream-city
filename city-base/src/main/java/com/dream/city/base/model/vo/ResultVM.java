package com.dream.city.base.model.vo;

import java.io.Serializable;

/**
 * 请求返回格式标准
 * 
 * @ClassName: ResultVM
 * @Description: TODO
 * @date 2018年6月15日
 */
public class ResultVM implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Integer CODE = 200;

	private Integer code;// 200成功 

	private String msg;// 不能返回null

	private Object data;

	public ResultVM() {

	}

	public ResultVM(Integer code) {
		this.code = code;
	}

	public ResultVM(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultVM(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}

	public ResultVM(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultVM(String msg, Object data) {
		// this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultVM(Object data) {
		this.data = data;
	}

	public static ResultVM error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static ResultVM error(String msg) {
		return error(500, msg);
	}

	public static ResultVM error(Integer code, String msg) {
		return new ResultVM(code, msg);
	}

	public static ResultVM ok(String msg) {
		return ok(CODE, msg);
	}

	public static ResultVM ok(Integer code, String msg) {
		return new ResultVM(code, msg);
	}

	public static ResultVM ok(Object data) {
		return new ResultVM(CODE, data);
	}

	public static ResultVM ok(String msg, Object data) {
		return new ResultVM(CODE, msg, data);
	}

	public static ResultVM ok() {
		return new ResultVM(CODE);
	}

	public static ResultVM info(Boolean ret) {
		if (ret) {
			return ResultVM.ok();
		} else {
			return ResultVM.error();
		}
	}

	public Integer getCode() {
		return code;
	}

	public ResultVM setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResultVM setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResultVM setData(Object data) {
		this.data = data;
		return this;
	}

	/**  
	 * @Title: toString  
	 * @Description: TODO
	 * @param @return    参数  
	 */  
	@Override
	public String toString() {
		return "ResultVM [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
