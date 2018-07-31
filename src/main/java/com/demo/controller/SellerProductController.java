package com.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.Service.ProductService;
import com.demo.dataobject.ProductInfo;
import com.demo.dto.OrderDTO;

/**
 * 卖家端商品
 * @author Alex
 *
 */

@Controller
@RequestMapping("seller/product")
public class SellerProductController {
	
	@Autowired
	private ProductService productService;

	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
			 @RequestParam(value = "size" ,defaultValue = "10") Integer size,
			 Map<String,Object> map){
		
		PageRequest pageable = new PageRequest(page - 1, size);
		Page<ProductInfo> orderDTOPage = productService.findAll(pageable);
		map.put("productInfoPage", orderDTOPage);	
		map.put("currentPage", page);
		map.put("size", size);
		return new ModelAndView("product/list", map);
	}
	
	@RequestMapping("/on_sale")
	public ModelAndView onSale(@RequestParam("productId") String productId,
			 Map<String,Object> map){
		try{
			productService.onSale(productId);
		}catch(Exception e){
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/product/list");
			return new ModelAndView("common/success", map);
		}
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success", map);
	}
	
	@RequestMapping("/off_sale")
	public ModelAndView offSale(@RequestParam("productId") String productId,
			 Map<String,Object> map){
		try{
			productService.offSale(productId);
		}catch(Exception e){
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/product/list");
			return new ModelAndView("common/success", map);
		}
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success", map);
	}
}
