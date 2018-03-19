package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

}
