package com.demo.enums;

public enum ResultEnum {
	
	SUCCESS(0,"订单查询成功"),
	
	PARAME_ERROR(1,"请求参数错误"),
	
	PRODUCT_NOT_EXIST(10,"商品不存在"),
	
	PRODUCT_STOCK_ERROR(11,"商品库存不存在"),
	
	ORDER_NOT_EXIST(12,"订单不存在"),
	
	ORDERDETAIL_NOT_EXIST(13,"订单不存在"),
	
	ORDER_STATTUS_ERROR(14,"订单状态布正确"),
	
	ORDER_UPDATE_FAIL(15,"订单更新失败"),
	
	ORDER_DETAIL_EMPTY(16,"订单为空"),
	
	ORDER_PAY_STATUS_ERROR(17,"订单支付状态错误"),
	
	ORDER_OWNER_ERROR(18,"该订单不属于当前用户"),
	
	ORDER_CANCLE_SUCCESS(22,"订单 取消成功"),
	
	ORDER_FINISH_SUCCESS(23,"订单 取消成功"),
	
	PRODUCT_STATUS_ERROR(24,"商品状态不正确")
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
