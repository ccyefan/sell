package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
		/**
		 * 通过关联外键字段(订单用户表)字段查询订单详情
		 * @param orderId
		 * @return
		 */
		List<OrderDetail> findByOrderId(String orderId);
}
