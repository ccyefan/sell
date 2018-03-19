package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {

}
