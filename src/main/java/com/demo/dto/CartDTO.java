package com.demo.dto;

/**
 * 购物车
 * @author Alex
 *
 */
public class CartDTO {

	/**产品id */
	private String productId;
	
	/**产品数量 */
	private Integer productQuatity;

	public CartDTO() {
		super();
	}

	public CartDTO(String productId, Integer productQuatity) {
		super();
		this.productId = productId;
		this.productQuatity = productQuatity;
	}



	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getProductQuatity() {
		return productQuatity;
	}

	public void setProductQuatity(Integer productQuatity) {
		this.productQuatity = productQuatity;
	}
}
