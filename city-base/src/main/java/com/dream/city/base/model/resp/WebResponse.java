package com.dream.city.base.model.resp;

import com.dream.city.base.model.enu.StatusCode;
import com.dream.city.base.utils.JSONHelper;

import java.io.Serializable;

/**
 * 
 * 对外统一返回数据格式
 * 
 * @author ligang
 *
 */
public class WebResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;// 状态码

	private String statusMsg;// 状态信息

	public WebResponse() {

	}

	public WebResponse(Integer code) {
		this.statusCode = code;
	}

	public WebResponse(Integer code, String msg) {
		this.statusCode = code;
		this.statusMsg = msg;
	}

	public WebResponse(Integer code, String msg, Object result) {
		this.statusCode = code;
		this.statusMsg = msg;
		this.data = result;
	}

	/**
	 * 结果
	 */
	private Object data;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static WebResponse error(Integer code, String msg) {
		return new WebResponse(code, msg);
	}

	public static WebResponse error(String msg) {
		return new WebResponse(StatusCode.BAD_REQUEST_CODE, msg, "");
	}

	public static WebResponse error(String msg, Object data) {
		return new WebResponse(StatusCode.SERVER_INTERNAL_ERROR_CODE, msg, data);
	}

	public static WebResponse ok(String msg) {
		return ok(StatusCode.SUCCESS_OK_CODE, msg);
	}

	public static WebResponse ok(Integer code, String msg) {
		return new WebResponse(code, msg);
	}

	public static WebResponse ok(String msg, Object result) {
		return new WebResponse(StatusCode.SUCCESS_OK_CODE, msg, result);
	}

	public static WebResponse ok(Object result) {
		return new WebResponse(StatusCode.SUCCESS_OK_CODE, "SUCCESS", result);
	}

	@SuppressWarnings("rawtypes")
	public <T> T coverData(Class<T> clz) {
		if (data instanceof String) {
			return (T) data;
		}
		String json = JSONHelper.toJson(data);
		return JSONHelper.fromJson(json, clz);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebResponse [statusCode=");
		builder.append(statusCode);
		builder.append(", statusMsg=");
		builder.append(statusMsg);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
