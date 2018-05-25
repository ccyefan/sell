package com.demo.controller;

import java.util.Map;

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

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

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
}
