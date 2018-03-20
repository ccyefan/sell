package com.demo.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {

//	Page<OrderMaster> findBuyerOpenid(String buyerOpenid , Pageable pageable);
	
	
}
