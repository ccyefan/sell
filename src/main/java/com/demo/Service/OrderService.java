package com.demo.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.dto.OrderDTO;

public interface OrderService {
	/**创建订单 */
	OrderDTO createOrderMaster(OrderDTO orderDTO);
	
	/**查询单个订单 */
	OrderDTO findOne(String orderId);
	
	/**查询订单列表 */
	Page<OrderDTO> findList(String buyerOpenid,Pageable pageable);
	
	/**取消订单 */
	OrderDTO cancle(OrderDTO orderDTO);
	
	/**完结订单 */
	OrderDTO finish(OrderDTO orderDTO);
	
	/**支付订单 */
	OrderDTO paid(OrderDTO orderDTO);
	
	/**查询订单 */
	Page<OrderDTO> findList(Pageable pageable);

}
