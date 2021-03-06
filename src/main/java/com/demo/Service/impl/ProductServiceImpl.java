package com.demo.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.Service.ProductService;
import com.demo.dataobject.ProductInfo;
import com.demo.dto.CartDTO;
import com.demo.enums.ProductStatusEnum;
import com.demo.enums.ResultEnum;
import com.demo.exception.SellException;
import com.demo.repository.ProductInfoRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Override
	public ProductInfo findOne(String productId) {
		return productInfoRepository.findOne(productId);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return productInfoRepository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return productInfoRepository.save(productInfo);
	}

	@Override
	@Transactional
	public void increaseStock(List<CartDTO> cartDTOList) {
		for(CartDTO cartDTO : cartDTOList){
			ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() + cartDTO.getProductQuatity();
			productInfo.setProductStock(result);
			
			productInfoRepository.save(productInfo);
		}
	}

	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cartDTOList) {
		for(CartDTO cartDTO : cartDTOList){
			ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
			if(cartDTO == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			
			Integer result = productInfo.getProductStock() - cartDTO.getProductQuatity();
			if(result < 0){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			
			productInfo.setProductStock(result);
			
			productInfoRepository.save(productInfo);
		}
	}

	//上架
	@Override
	public ProductInfo onSale(String productId) {
		ProductInfo productInfo = productInfoRepository.findOne(productId);
		if(productInfo == null){
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		if(productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}
		productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
		return productInfoRepository.save(productInfo);
	}

	//下架
	@Override
	public ProductInfo offSale(String productId) {
		ProductInfo productInfo = productInfoRepository.findOne(productId);
		if(productInfo == null){
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		if(productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}
		productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		return productInfoRepository.save(productInfo);
	}

}
