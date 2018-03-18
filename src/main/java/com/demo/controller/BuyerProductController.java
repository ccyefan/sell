package com.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Service.CategoryService;
import com.demo.Service.ProductService;
import com.demo.VO.ProductInfoVO;
import com.demo.VO.ProductVO;
import com.demo.VO.ResultVO;
import com.demo.dataobject.ProductCategory;
import com.demo.dataobject.ProductInfo;
import com.demo.utils.ResultVOUtil;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public ResultVO list(){
		//1.查询所有上架的商品
		List<ProductInfo> productInfoList = productService.findUpAll();
		//2.查询类目(一次性查询)
		List<Integer> categoryTypes = new ArrayList<Integer>();
			//2.1 传统的方式 循环,缺点效率低
			/*for(ProductInfo productInfo : productInfoList){
				categoryTypes.add(productInfo.getCategoryType());
			}*/
			//2.2 精简方法（java8,lambda)
		categoryTypes = productInfoList.stream()
				.map(e -> e.getCategoryType())
				.collect(Collectors.toList());
		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypes);
		//3.拼装
		List<ProductVO> productVOList = new ArrayList<ProductVO>();
		for(ProductCategory productCategory : productCategoryList){
			ProductVO productVO = new ProductVO();
			productVO.setCategoryName(productCategory.getCategoryName());
			productVO.setCategoryType(productCategory.getCategoryType());
			
			List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>(); 
			for(ProductInfo productInfo : productInfoList){
				if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo, productInfoVO);
					productInfoVOList.add(productInfoVO);
				}
			}
			productVO.setProductInfoVOList(productInfoVOList);
			productVOList.add(productVO);
		}
		
		ResultVO resultVO = new ResultVO();
		/*ProductVO productVO = new ProductVO();
		ProductInfoVO productInfoVO = new ProductInfoVO();
		productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
		resultVO.setData(Arrays.asList(productVO));*/
/*		resultVO.setData(productVOList);
		resultVO.setCode("0");
		resultVO.setMsg("成功");
		return resultVO;*/
		return ResultVOUtil.success(productVOList);
	}
}
