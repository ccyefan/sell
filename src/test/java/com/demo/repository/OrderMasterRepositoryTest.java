package com.demo.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dataobject.OrderMaster;
import com.demo.enums.OrderStatusEnum;
import com.demo.enums.PayStatusEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
	
	private final String 	OPENID = "123456";
	
	@Autowired
	private OrderMasterRepository repository;
	
//	@Test
	public void saveTest(){
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("4534");
		orderMaster.setBuyerName("aaa");
		orderMaster.setBuyerPhone("23244234");
		orderMaster.setBuyerAddress("fsa");
		orderMaster.setBuyerOpenid("123456");
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setOrderAmount(new BigDecimal(3.2));
		
		OrderMaster result = repository.save(orderMaster);
		Assert.assertNotNull(result);
		
	}
	
	@Test
	public void findByOrderidTest(){
		PageRequest request = new PageRequest(0, 3);
		Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
		Assert.assertNotEquals(0, result.getTotalElements());
		System.out.println(result.getTotalElements());
	}
}
