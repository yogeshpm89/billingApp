package com.prounited.billingapp.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	private long categoryId;
	
	@Column(name = "category_name", unique = true, nullable = false, length = 20)
	private String categoryName;
	
	@Column(name = "category_desc", unique = true, nullable = false, length = 30)
	private String categoryDesc;
	
	@Column(name = "is_active", unique = true, nullable = false, length = 1)
	private String isActive;
	
	@Column(name = "create_date", unique = true, nullable = false)
	private Date createDate;
	
	@Column(name = "create_user", unique = true, nullable = false, length = 20)
	private String createUser;
	
	@Column(name = "update_date", unique = true, nullable = false)
	private Date updateDate;
	
	@Column(name = "update_user", unique = true, nullable = false, length = 20)
	private String updateUser;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	//private Set<Item> items = new HashSet<Item>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Item> items = new HashSet<Item>(0);
	
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/*public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}*/
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryDesc=" + categoryDesc
				+ ", createDate=" + createDate + ", createUser=" + createUser
				+ ", updateDate=" + updateDate + ", updateUser=" + updateUser + "]";
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
}
