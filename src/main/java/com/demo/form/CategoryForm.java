package com.demo.form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CategoryForm {
	
	@NotNull(message="Id不能为空")
	private Integer categoryId;
	
	/**类目名称 */
	@NotNull(message="名字不能为空")
	private String categoryName;
	
	/**类目编号 */
	@NotNull(message="编号不能为空")
	private Integer categoryType;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
}
