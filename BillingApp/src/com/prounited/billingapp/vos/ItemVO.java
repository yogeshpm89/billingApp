package com.prounited.billingapp.vos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.prounited.billingapp.models.Category;
import com.prounited.billingapp.models.Item;

public class ItemVO implements java.io.Serializable {

	private long itemId;
	private String itemName;
	private String itemDesc;
	private String itemCode;
	private long itemQuantity;
	private float unitPrice;
	private long categoryId;
	private CategoryVO categoryVO;
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
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public CategoryVO getCategoryVO() {
		return categoryVO;
	}
	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static ItemVO getItemVO(Item item) {
		ItemVO itemVO = new ItemVO();
		itemVO.setItemId(item.getItemId());
		itemVO.setCategoryId(item.getCategoryId());
		itemVO.setCategoryName(item.getCategoryName());
		itemVO.setItemCode(item.getItemCode());
		itemVO.setItemDesc(item.getItemDesc());
		itemVO.setItemName(item.getItemName());
		itemVO.setItemQuantity(item.getItemQuantity());
		itemVO.setUnitPrice(item.getUnitPrice());
		itemVO.setCategoryVO(CategoryVO.getCategoryVO(item.getCategory()));
		
		return itemVO;
	}
	
	public static List<ItemVO> getItemVOs(List<Item> items) {
		List<ItemVO> itemVOs = new ArrayList<ItemVO>();
		for (Item item: items) {
			itemVOs.add(getItemVO(item));
		}
		return itemVOs;
	}
}
