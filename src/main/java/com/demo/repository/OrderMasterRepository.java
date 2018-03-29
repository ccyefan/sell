package com.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
	/**
	 * 当前 openId 下的所有订单
	 * @param buyerOpenid
	 * @param pageable
	 * @return
	 */
	Page<OrderMaster> findByBuyerOpenid(String buyerOpenid , Pageable pageable);
	
}
