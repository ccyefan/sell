package com.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.Service.CategoryService;
import com.demo.dataobject.ProductCategory;
import com.demo.form.CategoryForm;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 类目列表
	 * @param map
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(Map<String,Object> map){
		
		List<ProductCategory> categoryList = categoryService.findAll();
		map.put("categoryList", categoryList);
		return new ModelAndView("category/list",map);
	}
	
	/**
	 * 类目信息
	 * @param categoryid
	 * @param map
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value = "categoryid",required = false) Integer categoryId,
								Map<String,Object> map
							){
		if(categoryId != null){
			ProductCategory productCategory = categoryService.findOneCategory(categoryId);
			map.put("category", productCategory);
		}
		return new ModelAndView("category/index",map);
	}
	
	@RequestMapping("/save")
	public ModelAndView save(@Valid CategoryForm categoryForm,
							BindingResult bindingResult,
							Map<String,Object> map
							){
		
		if(bindingResult.hasErrors()){
			map.put("url", "/sell/seller/category/index");
			return new ModelAndView("common/error",map);
		}
		
		ProductCategory productCategory = new  ProductCategory();
		try{
			// 如果id为空，则是新增.否则是修改
			if(!StringUtils.isEmpty(categoryForm.getCategoryId())){
				productCategory = categoryService.findOneCategory(categoryForm.getCategoryId());
			}else{
				categoryForm.setCategoryId(productCategory.getCategoryId());
			}
			BeanUtils.copyProperties(categoryForm, productCategory);
			categoryService.save(productCategory);
		}catch (Exception e){
			map.put("msg", e.getMessage());
			map.put("url", "/sell/seller/category/index");
			return new ModelAndView("common/error", map);
		}
		map.put("url", "/sell/seller/category/list");
		return new ModelAndView("common/success", map);
	}
}
