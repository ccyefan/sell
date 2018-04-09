package com.demo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.mockito.internal.util.collections.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Service.OrderService;
import com.demo.VO.ResultVO;
import com.demo.convertor.OrderForm2OrderDTOConverter;
import com.demo.dto.OrderDTO;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;
import com.demo.form.OrderForm;
import com.demo.utils.ResultVOUtil;


@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
	private final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	//创建订单
//	@RequestMapping(path = "/create",method = RequestMethod.POST)
	@PostMapping("/create")
	public ResultVO<Map<String,String>> create(@Validated OrderForm orderForm,
												BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			logger.error("[创建订单] 参数不正确 , orderForm = {} ",orderForm.toString());
			throw new SellException(ResultEnum.PARAME_ERROR.getCode(),
									bindingResult.getFieldError().getCode());
		}
		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
			logger.error("[购物车不能为空]");
			throw new SellException(ResultEnum.PARAME_ERROR);
		}
		OrderDTO createResult = orderService.createOrderMaster(orderDTO);
		
		Map<String,String> map = new HashMap<>();
		map.put("orderId", createResult.getOrderId());
		
		return ResultVOUtil.success(map);
	}
	//订单列表
	
	//订单详情
	
	//取消订单
}
