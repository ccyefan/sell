package com.demo.Service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.demo.Service.OrderService;
import com.demo.Service.ProductService;
import com.demo.convertor.OrderMaster2OrderDTOConvertors;
import com.demo.dataobject.OrderDetail;
import com.demo.dataobject.OrderMaster;
import com.demo.dataobject.ProductInfo;
import com.demo.dto.CartDTO;
import com.demo.dto.OrderDTO;
import com.demo.enums.OrderStatusEnum;
import com.demo.enums.PayStatusEnum;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;
import com.demo.repository.OrderDetailRepository;
import com.demo.repository.OrderMasterRepository;
import com.demo.utils.KeyUtil;
@Service	
@Slf4j
public class OrderServiceImpl implements OrderService{
	
	private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);	

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
			orderAmount = productInfo.getProductPrice()
					.multiply(new BigDecimal(orderDetail.getProductQuantity()))
					.add(orderAmount);
			// 订单详情入库(orderDetail)
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			orderDetail.setOrderId(orderId);
			orderDetailRepository.save(orderDetail);
			//CartDTO cartDTO = new CartDTO(orderDetail.getOrderId(),orderDetail.getProductQuantity());
			//cartDTOList.add(cartDTO);
		}
		//3.写入订单数据库(orderMaster)
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderId(orderId);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
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
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
		
		List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConvertors.convert(orderMasterPage.getContent());
		
		Page<OrderDTO> orderDTO = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
		
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO cancle(OrderDTO orderDTO) {
		OrderMaster orderMaster = new OrderMaster();
		//判断订单状态
		if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getClass())){
			logger.info("[取消订单] 订单状态不正确,sorderId= {} ,orderStatus ={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
			throw new SellException(ResultEnum.ORDER_STATTUS_ERROR);
		}
		//修改订单 状态
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster result = orderMasterRepository.save(orderMaster);
		if(result == null){
			logger.info("[取消订单] 跟新失败 , orderMaster = {}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		//返回库存(加库存)
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
			logger.error("[取消订单] 订单中无商品详情, orderDTO = {}",orderDTO);
			throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> 
			new CartDTO(e.getProductId(),e.getProductQuantity())
		).collect(Collectors.toList());
		productService.increaseStock(cartDTOList);
		//如果支付，需要退款
		if(orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
			
		}
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
		//判断订单状态
		if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.info("[完结订单] orderId = {}, orderStatus = {}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATTUS_ERROR);
		}
		//修改订单状态
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateresult = orderMasterRepository.save(orderMaster);
		if(updateresult == null){
			logger.info("[完结订单] orderMaster = {} ",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		//判断订单状态
		if( ! orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
			logger.info("[订单支付完成] 订单状态不正确 ，orderId = {}, orderStatus ={} ",orderDTO.getOrderId(),orderDTO.getOrderStatus());
			throw new SellException(ResultEnum.ORDER_STATTUS_ERROR);
		}
		//判断支付状态
		if( ! orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
			logger.info("[订单支付完成] 订单支付状态不正确 ，payStatus = {} ",orderDTO.getPayStatus());
			throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
		}
		//修改支付状态
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateresult = orderMasterRepository.save(orderMaster);
		if(updateresult == null){
			logger.info("[完结订单] orderMaster = {} ",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}
}
