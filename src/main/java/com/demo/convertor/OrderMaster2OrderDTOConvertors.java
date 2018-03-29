package com.demo.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.demo.dataobject.OrderMaster;
import com.demo.dto.OrderDTO;

public class OrderMaster2OrderDTOConvertors {

	public static OrderDTO convert(OrderMaster orderMaster){
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
	}
	
	public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
		return orderMasterList.stream().map(e -> 
			convert(e)
		).collect(Collectors.toList());
	}
	
}
