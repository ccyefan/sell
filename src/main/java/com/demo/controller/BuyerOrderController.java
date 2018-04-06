package com.demo.controller;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Service.OrderService;
import com.demo.VO.ResultVO;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;
import com.demo.form.OrderForm;


@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
	private final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	//创建订单
	public ResultVO<Map<String,String>> create(@Validated OrderForm orderForm,
												BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			logger.error("[创建订单] 参数不正确 , orderForm = {} ",orderForm );
			throw new SellException(ResultEnum.PARAME_ERROR.getCode(),
									bindingResult.getFieldError().getCode());
		}
		
		return null;
	}
	//订单列表
	
	//订单详情
	
	//取消订单
}
