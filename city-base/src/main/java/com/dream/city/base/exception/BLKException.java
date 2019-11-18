package com.dream.city.base.exception;

/**
 * 整体系统尽量使用运行时异常，简化异常的继承结构，尽量通过Code、Msg来携带信息
 * 
 * @author duanshao
 */
public class BLKException extends RuntimeException {
	private static final long serialVersionUID = -2742295823094596726L;

	private int code;
	private String msg;

	public BLKException() {
		super();
	}

	public BLKException(int code, String msg) {
		this(code, msg, null);
	}

	public BLKException(int code, String msg, Throwable e) {
		super(msg, e);
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "GZException [code=" + code + ", msg=" + msg + "]";
	}
}
