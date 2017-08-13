package com.prounited.billingapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="items")
public class Item implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private long itemId;
	
	@Column(name = "item_name", unique = true, nullable = false, length = 20)
	private String itemName;
	
	@Column(name = "item_desc", unique = true, nullable = false, length = 30)
	private String itemDesc;
	
	@Column(name = "item_code", unique = true, nullable = false, length = 30)
	private String itemCode;
	
	@Column(name = "item_quantity", unique = true, nullable = false)
	private long itemQuantity;
	
	/*@Column(name = "item_discount", unique = true, nullable = false)
	private float itemDiscount;*/
	
	@Column(name = "unit_price", unique = true, nullable = false)
	private float unitPrice;
	
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

	@Column(name = "category_id", unique = true, nullable = false)
	private long categoryId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false, insertable= false, updatable=false)
	private Category category;
	
	@Transient
	private String categoryName;
	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/*public float getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(float itemDiscount) {
		this.itemDiscount = itemDiscount;
	}*/

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCategoryName() {
		String categoryName = this.getCategory().getCategoryName();
		if (categoryName == null) {
			categoryName = this.categoryName;
		}
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public String getCategoryDesc() {
		return this.getCategory().getCategoryDesc();
	}
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemDesc=" + itemDesc + ", itemCode=" + itemCode
				+ ", itemQuantity=" + itemQuantity + ", unitPrice=" + unitPrice + ", createDate="
				+ createDate + ", createUser=" + createUser + ", updateDate="
				+ updateDate + ", updateUser=" + updateUser + ", categoryId="
				+ categoryId + "]";
	}

}
