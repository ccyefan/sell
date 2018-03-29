package com.demo.Service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.Service.OrderService;
import com.demo.Service.ProductService;
import com.demo.dataobject.OrderDetail;
import com.demo.dataobject.OrderMaster;
import com.demo.dataobject.ProductInfo;
import com.demo.dto.CartDTO;
import com.demo.dto.OrderDTO;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;
import com.demo.repository.OrderDetailRepository;
import com.demo.repository.OrderMasterRepository;
import com.demo.utils.KeyUtil;
@Service	
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	
	/**
	 * 参考API文档 创建订单
	 * POST /sell/buyer/order/create
	 * 参数
	 * name: "张三"
	 * phone: "18868822111"
	 * address: "慕课网总部"
	 * openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
	 * items: [{
	 * 	productId: "1423113435324",
	 *  productQuantity: 2 //购买数量
	 * }]
	 * 	 
	 */
	@Override
	@Transactional
	public OrderDTO createOrderMaster(OrderDTO orderDTO) {
		
		String orderId = KeyUtil.genUniqueKey();
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		
		//List<CartDTO> cartDTOList = new ArrayList<CartDTO>();
		
		//1.查询商品(数量，价格)
		for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
			ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			//2.计算总价  价格 * 数量
			orderAmount = orderDetail.getProductPrice()
					.multiply(new BigDecimal(orderDetail.getProductQuantity()))
					.add(orderAmount);
			// 订单详情入库(orderDetail)
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			orderDetail.setOrderId(orderId);
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailRepository.save(orderDetail);
			//CartDTO cartDTO = new CartDTO(orderDetail.getOrderId(),orderDetail.getProductQuantity());
			//cartDTOList.add(cartDTO);
		}
		//3.写入订单数据库(orderMaster)
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(orderAmount);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMasterRepository.save(orderMaster);
		
		//4.扣库存
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
			new CartDTO(e.getProductId(),e.getProductQuantity())
		).collect(Collectors.toList());
		
		productService.decreaseStock(cartDTOList);
		
		return orderDTO;
	}

	@Override
	public OrderDTO findOne(String orderId) {
		OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
		if(orderMaster == null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		
		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if(orderDetailList == null){
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO cancle(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
