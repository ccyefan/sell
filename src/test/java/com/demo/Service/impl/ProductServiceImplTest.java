package com.demo.Service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dataobject.ProductInfo;
import com.demo.enums.ProductStatusEnum;

/**
 * 
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
//	@Test
	public void findOne(){
		ProductInfo result = productServiceImpl.findOne("123456");
		Assert.assertEquals("123456", result.getProductId());;
	}
	
//	@Test
	public void findUpAllTest(){
		List<ProductInfo> list = productServiceImpl.findUpAll();
		Assert.assertNotEquals(0, list.size());
	}
	
//	@Test
	public void findAll(){
		PageRequest pageRequest = new PageRequest(0, 2);
		Page<ProductInfo> list = productServiceImpl.findAll(pageRequest);
		System.out.println(list.getTotalElements());
		Assert.assertNotEquals(0, list.getSize());
	}
	
	//@Test
	public void save(){
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("1234567");
		productInfo.setProductName("皮皮虾");
		productInfo.setProductPrice(new BigDecimal(3.2));
		productInfo.setProductStock(100);
		productInfo.setProductDescription("很好喝的粥");
		productInfo.setProductIcon("http:xxx.jsp");
		productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		productInfo.setCategoryType(2);		
		ProductInfo result = productServiceImpl.save(productInfo);
		Assert.assertNotNull(result);
	}
	//@Test
	public void onSaleTest(){
		ProductInfo productInfo = productServiceImpl.onSale("123456");
		Assert.assertEquals(productInfo.getProductStatus(), ProductStatusEnum.UP.getCode());
	}
	@Test
	public void offSaleTest(){
		ProductInfo productInfo = productServiceImpl.offSale("123456");
		Assert.assertEquals(productInfo.getProductStatus(), ProductStatusEnum.DOWN.getCode());
	}
}
