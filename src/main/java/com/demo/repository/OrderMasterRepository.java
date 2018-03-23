package com.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {

	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid , Pageable pageable);
	
}
