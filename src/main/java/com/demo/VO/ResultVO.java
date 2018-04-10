package com.demo.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Administrator
 * @param <T>
 *
 */
//@JsonInclude(Include.NON_NULL)
public class ResultVO<T> {
	/**错误码 */
	private String msg;
	/**提示信息 */
	private Integer code;
	/**数据 */
	private T data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}
