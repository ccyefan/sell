package com.demo.Service;

import com.demo.dto.OrderDTO;

public interface BuyerService {
	
	OrderDTO findOne(String openId,String orderId); 
	
	OrderDTO cancle(String openId,String orderId);

}
