package com.demo.dataobject;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 目类
 * @author Alex
 *
 */
//@Table(name = "procut_category")
@Entity
@DynamicUpdate  //时间对应动态更新
public class ProductCategory {
	
	/** 类目id. */
	@Id
	@GeneratedValue
	private Integer categoryId;
	
	/**类目名称 */
	private String categoryName;
	
	/**类目编号 */
	private Integer categoryType;

	/**创建时间 */
	private Date createTime;
	
	/**修改时间 */
	private Date updateTime;
	
	public ProductCategory() {
		super();
	}

	public ProductCategory(String categoryName, Integer categoryType) {
		super();
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}

	@Override
	public String toString() {
		return "ProductCategory [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryType=" + categoryType + "]";
	}

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

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
