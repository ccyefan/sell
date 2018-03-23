package com.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dataobject.OrderDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

	@Autowired
	private OrderDetailRepository repository;
	
	@Test
	public void saveOrderDetail(){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId("4534");
		orderDetail.setDetailId("001");
		orderDetail.setProductIcon("xxx.png");
		orderDetail.setProductId("123");
		orderDetail.setProductName("卤肉饭");
		orderDetail.setProductPrice(new BigDecimal(18.5));
		orderDetail.setProductQuantity(50);
		
		OrderDetail result = repository.save(orderDetail);
		Assert.assertNotNull(result);
	}
}
