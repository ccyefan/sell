package com.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.dataobject.ProductInfo;
import com.demo.dto.CartDTO;

public interface ProductService {
	
	ProductInfo findOne(String productId);
	
	/**查询所有上架商品 */
	List<ProductInfo> findUpAll();
	
	Page<ProductInfo> findAll(Pageable pageable);
	
	ProductInfo save(ProductInfo productInfo);
	
	//加库存
	void increaseStock(List<CartDTO> cartDTOList);
	//减库存
	void decreaseStock(List<CartDTO> cartDTOList);
	
	//上架
	ProductInfo onSale(String productId);
	
	//下架
	ProductInfo offSale(String productId);
	
}
