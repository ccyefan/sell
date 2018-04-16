package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Service.BuyerService;
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
	
	@Autowired
	private BuyerService buyerService;
	
	//创建订单
	@RequestMapping(path = "/create",method = RequestMethod.POST)
//	@PostMapping("/create")
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
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam(value= "openid") String openid,
											@RequestParam(value = "page",defaultValue = "0") Integer page,
											@RequestParam(value = "size",defaultValue = "10") Integer size){
		if(StringUtils.isEmpty(openid)){
			logger.error("[查询订单列表] openid为空");
			throw new SellException(ResultEnum.PARAME_ERROR);
		}
		Pageable pageable = new PageRequest(page, size);
		Page<OrderDTO> orderpage = orderService.findList(openid, pageable);
		// data -> Long
		return ResultVOUtil.success(orderpage.getContent());
		/*ResultVO resultVO = new ResultVO();
		resultVO.setCode(0);
		resultVO.setMsg("");
		return resultVO;*/
	}
	//订单详情
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam(value = "openid") String openid,
									 @RequestParam(value = "orderid") String orderid){
		//OrderDTO orderDTO = orderService.findOne(orderid);
		
		OrderDTO orderDTO = buyerService.findOne(openid,orderid);
		
		return ResultVOUtil.success(orderDTO);
	}
	//取消订单
	@PostMapping("/cancle")
	public ResultVO cancle(@RequestParam(value = "openid") String openid,
							@RequestParam(value = "orderid") String orderid){
		//OrderDTO orderDTO = orderService.findOne(orderid);
		//orderService.cancle(orderDTO);
		
		buyerService.cancle(openid, orderid);
		
		return ResultVOUtil.success();
		
	}
}
