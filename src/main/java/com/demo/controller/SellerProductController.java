package com.demo.controller;

import java.text.Normalizer.Form;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.Service.ProductService;
import com.demo.dataobject.ProductCategory;
import com.demo.dataobject.ProductInfo;
import com.demo.exception.SellException;
import com.demo.form.ProductForm;
import com.demo.repository.ProductCategoryRepository;
import com.demo.utils.KeyUtil;

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
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;

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
	
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
			 Map<String,Object> map){
		
		if(!StringUtils.isEmpty(productId)){
			ProductInfo productInfo = productService.findOne(productId);
			map.put("productInfo", productInfo);
		}
		
		List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
		map.put("productCategoryList", productCategoryList);
		return new ModelAndView("product/index", map);
	}
	
	@RequestMapping("/save")
	public ModelAndView save(@Valid ProductForm productForm,
									BindingResult bindingResult,
									Map<String,Object> map
							){
		if(bindingResult.hasErrors()){
			map.put("url", "/sell/seller/product/index");
			return new ModelAndView("common/error", map);
		}
		
		
		ProductInfo productInfo = new  ProductInfo();
		try{
			// 如果id为空，则是新增.否则是修改
			if(!StringUtils.isEmpty(productForm.getProductId())){
				productInfo = productService.findOne(productForm.getProductId());
			}else{
				productForm.setProductId(KeyUtil.genUniqueKey());;
			}
			BeanUtils.copyProperties(productForm, productInfo);
			productService.save(productInfo);
		}catch (SellException e){
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/product/index");
			return new ModelAndView("common/error", map);
		}
		map.put("url", "/sell/seller/product/list");
		return new ModelAndView("common/success", map);
	}
}
