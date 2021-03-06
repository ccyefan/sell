package com.demo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.dataobject.OrderDetail;
import com.demo.enums.OrderStatusEnum;
import com.demo.enums.PayStatusEnum;
import com.demo.utils.EnumUtils;
import com.demo.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonInclude(Include.NON_NULL)
public class OrderDTO {
	private String orderId;
	/**买家名字 */
	private String buyerName;
	/**买家电话 */
	private String buyerPhone;
	/**买家地址 */
	private String buyerAddress;
	/**买家微信openid */
	private String buyerOpenid;
	/**订单总金额 */
	private BigDecimal orderAmount;
	/**订单状态，默认为0新下单 */
	private Integer orderStatus ;
	/**支付状态， 默认为0未支付*/
	private Integer payStatus ;
	/**创建时间 */
	@JsonSerialize(using = Date2LongSerializer.class)
	private Date createTime;
	/**修改时间 */
	@JsonSerialize(using = Date2LongSerializer.class)
	private Date updateTime;
	
	private List<OrderDetail> OrderDetailList = new ArrayList<>();
	@JsonIgnore
	public OrderStatusEnum getOrderStatusEnum(){
		return EnumUtils.getByCode(orderStatus, OrderStatusEnum.class);
	}
	@JsonIgnore
	public PayStatusEnum getPayStatusEnum(){
		return EnumUtils.getByCode(payStatus, PayStatusEnum.class);
	}
	
	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", buyerName=" + buyerName
				+ ", buyerPhone=" + buyerPhone + ", buyerAddress="
				+ buyerAddress + ", buyerOpenid=" + buyerOpenid
				+ ", orderAmount=" + orderAmount + ", orderStatus="
				+ orderStatus + ", payStatus=" + payStatus + ", createTime="
				+ createTime + ", updateTime=" + updateTime
				+ ", OrderDetailList=" + OrderDetailList + "]";
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerOpenid() {
		return buyerOpenid;
	}

	public void setBuyerOpenid(String buyerOpenid) {
		this.buyerOpenid = buyerOpenid;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<OrderDetail> getOrderDetailList() {
		return OrderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		OrderDetailList = orderDetailList;
	}
	
	
}
