package com.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Service.CategoryService;
import com.demo.dataobject.ProductCategory;
import com.demo.repository.ProductCategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ProductCategoryRepository repository;
	
	@Override
	public ProductCategory findOneCategory(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(
			List<Integer> CategoryTypes) {
		return repository.findByCategoryTypeIn(CategoryTypes);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return repository.save(productCategory);
	}

}
