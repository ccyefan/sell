package com.demo.convertor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.dataobject.OrderDetail;
import com.demo.dto.OrderDTO;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;
import com.demo.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OrderForm2OrderDTOConverter {
	private final static Logger logger = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            logger.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAME_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
