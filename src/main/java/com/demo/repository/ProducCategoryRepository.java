package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.ProductCategory;

public interface ProducCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
