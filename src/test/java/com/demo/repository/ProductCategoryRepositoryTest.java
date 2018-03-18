package com.demo.repository;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.dataobject.ProductCategory;

/**
 * 
 * @author Alex
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
	
	@Autowired
	private ProductCategoryRepository repository;
	
	@Test
	public void findOne(){
		ProductCategory productCategory = repository.findOne(1);
		System.out.println(productCategory.toString());
	}
	
//	@Test	
	@Transactional
	public void save(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("女生最爱");
		productCategory.setCategoryType(9);
		ProductCategory result = repository.save(productCategory);
		Assert.assertNotNull(result);
	}

//	@Test
	public void update(){
		ProductCategory productCategory = repository.findOne(3);
		productCategory.setCategoryName("男女不都爱");
		repository.save(productCategory);
	}
	
	@Test
	public void findByCategoryType(){
		List<Integer> list = Arrays.asList(3,4,5);
		List<ProductCategory> restult = repository.findByCategoryTypeIn(list);
		Assert.assertNotEquals(0, restult.size());
	}
}
