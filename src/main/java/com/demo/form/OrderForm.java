package com.demo.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Administrator
 *
 */
public class OrderForm {
	/**
	 * 买家姓名
	 */
	@NotEmpty(message = "姓名必填")
	private String name;
	/**
	 * 买家电话
	 */
	@NotEmpty(message = "电话必填")
	private String phone;
	/**
	 * 买家地址
	 */
	@NotEmpty(message = "地址必填")
	private String address;
	/**
	 * 卖家微信openId
	 */
	@NotEmpty(message ="openid必填")
	private String openid;
	/**
	 * 购物车
	 */
	@NotEmpty(message = "购物车不能为空")
	private String items;
	
	public OrderForm() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
}
