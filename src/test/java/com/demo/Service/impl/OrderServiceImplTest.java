package com.demo.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dataobject.OrderDetail;
import com.demo.dto.OrderDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Test
	public void create(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("张小伟");
		orderDTO.setBuyerAddress("北京海淀");
		orderDTO.setBuyerOpenid("1101110");
		orderDTO.setBuyerPhone("13673676789");
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductId("123456");
		orderDetail.setProductQuantity(2);
		
		orderDetailList.add(orderDetail);
		orderDTO.setOrderDetailList(orderDetailList);
		orderServiceImpl.createOrderMaster(orderDTO);
	}
	
}
