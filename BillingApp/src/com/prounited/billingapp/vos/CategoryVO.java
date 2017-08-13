package com.prounited.billingapp.vos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.prounited.billingapp.models.Category;

public class CategoryVO {

	private long categoryId;
	private String categoryName;
	private String categoryDesc;
	private Set<ItemVO> itemVOs = new HashSet<ItemVO>(0);
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public Set<ItemVO> getItemVOs() {
		return itemVOs;
	}
	public void setItemVOs(Set<ItemVO> itemVOs) {
		this.itemVOs = itemVOs;
	}
	
	public static CategoryVO getCategoryVO(Category category) {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId(category.getCategoryId());
		categoryVO.setCategoryName(category.getCategoryName());
		categoryVO.setCategoryDesc(category.getCategoryDesc());
		return categoryVO;
	}
	
	public static List<CategoryVO> getCategoryVOs(List<Category> categories) {
		List<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
		for (Category category: categories) {
			categoryVOs.add(getCategoryVO(category));
		}
		
		return categoryVOs;
	}
}
