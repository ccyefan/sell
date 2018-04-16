package com.demo.Service.impl;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Service.BuyerService;
import com.demo.Service.OrderService;
import com.demo.dto.OrderDTO;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService{
	private final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);
	
	@Autowired
	private OrderService orderService;

	@Override
	public OrderDTO findOne(String openId, String orderId) {
		return CheckOrderOwner(openId, orderId);
	}

	@Override
	public OrderDTO cancle(String openId, String orderId) {
		
		OrderDTO orderDTO = CheckOrderOwner(openId, orderId);
		if(orderDTO == null){
			logger.error("[取消订单] 查不到该订单，orderId={}",orderId);
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return orderService.cancle(orderDTO);
	}
	
	private OrderDTO CheckOrderOwner(String openId, String orderId) {
		OrderDTO orderDTO = orderService.findOne(orderId);
		if(orderDTO == null){
			return null;
		}
		if(! orderDTO.getBuyerOpenid().equals(openId)){
			logger.error("[查询订单] 订单的openid不一致， orderid = {} ，orderDTO = {}", orderId,orderDTO);
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderDTO;
	}	

}
