package com.demo.dataobject;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.demo.enums.OrderStatusEnum;

@Entity
public class OrderMaster {
	/**orderid */
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
	private Integer order_status = OrderStatusEnum.NEW.getCode();
	/**支付状态， 默认为0未支付*/
	private Integer payStatus;
	
}
