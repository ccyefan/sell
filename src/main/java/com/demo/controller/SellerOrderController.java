package com.demo.controller;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.Service.OrderService;
import com.demo.dto.OrderDTO;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
	private Logger logger = LoggerFactory.getLogger(SellerOrderController.class);

	@Autowired
	private OrderService orderService;
	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
							 @RequestParam(value = "size" ,defaultValue = "10") Integer size,
							 Map<String,Object> map){
		PageRequest pageable = new PageRequest(page - 1, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(pageable);
		map.put("orderDTOPage", orderDTOPage);	
		map.put("currentPage", page);
		map.put("size", size);
		return new ModelAndView("order/list", map);
	}
	
	/**
	 * 取消订单
	 * @param orderId
	 * @return
	 */
	@GetMapping("/cancle")
	public ModelAndView cancle(@RequestParam("orderId") String orderId,
								Map<String,Object> map){
		try{
			OrderDTO orderDTO = orderService.findOne(orderId);
			orderService.cancle(orderDTO);
		}catch(SellException e){
			logger.error("[取消订单] 发生错误 {}",e);
			map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("common/error",map);
		}
		map.put("msg", ResultEnum.ORDER_CANCLE_SUCCESS.getMessage());
		map.put("url", "/sell/seller/order/list");
		return new ModelAndView("common/success");
	}
	
	@GetMapping("/detail")
	public ModelAndView detail(@RequestParam("orderId") String orderId,
								Map<String,Object> map){
		try{
			OrderDTO orderDTO = orderService.findOne(orderId);
			map.put("orderDTO", orderDTO);
		}catch(SellException e){
			logger.error("[卖家端查询订单] 发生错误 {}",e);
			map.put("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
			map.put("url", "/sell/seller/order/list");
			return new ModelAndView("common/error",map);
		}
		return new ModelAndView("common/detail",map);
	}
}
