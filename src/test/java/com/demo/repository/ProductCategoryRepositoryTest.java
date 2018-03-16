package com.demo.repository;

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
	private ProducCategoryRepository repository;
	
	@Test
	public void findOne(){
		ProductCategory productCategory = repository.findOne(1);
		System.out.println(productCategory.toString());
	}
	
	@Test
	public void save(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("女生最爱");
		productCategory.setCategoryType("5");
		repository.save(productCategory);
	}

}
