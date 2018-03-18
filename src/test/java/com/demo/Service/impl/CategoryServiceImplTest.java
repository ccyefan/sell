package com.demo.Service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dataobject.ProductCategory;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
//	@Test
	public void findOneCategoryTest(){
		ProductCategory productCategory = categoryServiceImpl.findOneCategory(2);
		Assert.assertEquals(new Integer(2), productCategory.getCategoryId());
	}
	
//	@Test
	public void findAllTest(){
		List<ProductCategory> list = categoryServiceImpl.findAll();
		Assert.assertNotEquals(0,list.size());
	}
	
//	@Test
	public void findByCategoryTypeInTest(){
		List<Integer> categoryTypes = Arrays.asList(2,3,4);
		List<ProductCategory> list = categoryServiceImpl.findByCategoryTypeIn(categoryTypes);
		Assert.assertNotEquals(0, list.size());
	}
	
	@Test
	public void saveTest(){
		ProductCategory productCategory = new ProductCategory("小孩喜欢" ,11);
		ProductCategory result = categoryServiceImpl.save(productCategory);
		Assert.assertNotNull(result);
	}
}
