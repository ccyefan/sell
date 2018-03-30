package com.demo.Service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.LoggerTest;
import com.demo.dataobject.OrderDetail;
import com.demo.dto.OrderDTO;
import com.demo.enums.OrderStatusEnum;
import com.demo.enums.PayStatusEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
	
	private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);	
	
	private final String BYER_OPENID = "1018302";
	
	private final String ORDER_ID = "1522380819195563356";
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
//	@Test
	public void create(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("张小伟");
		orderDTO.setBuyerAddress("北京海淀");
		orderDTO.setBuyerOpenid(BYER_OPENID);
		orderDTO.setBuyerPhone("13673676789");
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductId("123456");
		orderDetail.setProductQuantity(2);
		orderDetail.setProductPrice(new BigDecimal(4.6));
		
		orderDetailList.add(orderDetail);
		orderDTO.setOrderDetailList(orderDetailList);
		OrderDTO result = orderServiceImpl.createOrderMaster(orderDTO);
		logger.info("[创建订单] result = {}" ,result.toString());
		logger.info("aa", result.toString());
	}
	
//	@Test
	public void findOneTest(){
		OrderDTO result = orderServiceImpl.findOne(ORDER_ID);
		logger.info("[查询单个订单] result = {}" ,result.toString());
		Assert.assertEquals(ORDER_ID, result.getOrderId());
	}
	
//	@Test
	public void findList(){
		PageRequest pageRequest = new PageRequest(0, 2);
		Page<OrderDTO> OrderDTOPage = orderServiceImpl.findList(BYER_OPENID, pageRequest);
		Assert.assertNotEquals(0, OrderDTOPage.getTotalElements());
	}
	
//	@Test
	public void cancle(){
		OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
		OrderDTO result = orderServiceImpl.cancle(orderDTO);
		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTO.getOrderStatus());
	}
	
//	@Test
	public void finish(){
		OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
		OrderDTO result = orderServiceImpl.finish(orderDTO);
		Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), orderDTO.getOrderStatus());
	}
	
	@Test
	public void paid(){
		OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
		OrderDTO result = orderServiceImpl.paid(orderDTO);
		Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderDTO.getPayStatus());
	}
}
