package com.demo.Service;

import java.util.List;

import com.demo.dataobject.ProductCategory;

public interface CategoryService {
	
	ProductCategory findOneCategory(Integer id);
	
	List<ProductCategory> findAll();
	
	List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypes);
	
	ProductCategory save(ProductCategory productCategory);
}
