package com.demo.enums;

public enum ResultEnum {
	PRODUCT_NOT_EXIST(10,"商品不存在"),
	
	PRODUCT_STOCK_ERROR(11,"商品库存不存在"),
	
	ORDER_NOT_EXIST(12,"订单不存在"),
	
	ORDERDETAIL_NOT_EXIST(13,"订单不存在"),
	;
	
	private Integer code;
	
	private String message;

	private ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}