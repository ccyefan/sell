package com.demo.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductVO {
	
	@JsonProperty("name")
	private String categoryName;
	
	@JsonProperty("type")
	private Integer categoryType;
	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;
	public ProductVO() {
	}
	public ProductVO(String categoryName, Integer categoryType,
			List<ProductInfoVO> productInfoVOList) {
		super();
		this.categoryName = categoryName;
		this.categoryType = categoryType;
		this.productInfoVOList = productInfoVOList;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}
	public List<ProductInfoVO> getProductInfoVOList() {
		return productInfoVOList;
	}
	public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
		this.productInfoVOList = productInfoVOList;
	}
}
