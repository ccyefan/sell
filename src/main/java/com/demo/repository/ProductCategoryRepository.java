package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
	/**
	 * 传入 类型列表
	 * @param categoryType
	 * @return
	 */

	// In findByAgeIn(Collection<Age> ages) … where x.age in ?1

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
}
