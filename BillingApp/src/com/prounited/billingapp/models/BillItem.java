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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="bill_items")
public class BillItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_item_id", unique = true, nullable = false)
	private long billItemId;
	
	@Column(name = "bill_id", nullable = false)
	private long billId;
	
	@Transient
	private long categoryId;
	@Transient
	private String categoryName;
	@Transient
	private String itemName;
	@Transient
	private String itemDesc;
	@Transient
	private String itemCode;
	@Transient
	private float unitPrice;
	
	@Column(name = "item_id", nullable = false)
	private long itemId;
	
	@Column(name = "item_quantity", nullable = false)
	private long itemQuantity;
	
	@Column(name = "discount", nullable = false)
	private float discount;
	
	@Column(name = "total_amount", nullable = false)
	private float totalAmount;
	
	@Column(name = "final_amount", nullable = false)
	private float finalAmount;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	@Column(name = "create_user", nullable = false, length = 20)
	private String createUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bill_id", nullable = false, insertable= false, updatable=false)
	private Bill bill;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", nullable = false, insertable= false, updatable=false)
	private Item item;
	
	public long getBillItemId() {
		return billItemId;
	}

	public void setBillItemId(long billItemId) {
		this.billItemId = billItemId;
	}

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

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemid(long itemId) {
		this.itemId = itemId;
	}

	public long getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(long itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(float finalAmount) {
		this.finalAmount = finalAmount;
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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "BillItem [billItemId=" + billItemId + ", billId=" + billId
				+ ", categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", itemName=" + itemName + ", itemDesc="
				+ itemDesc + ", itemCode=" + itemCode + ", unitPrice="
				+ unitPrice + ", itemId=" + itemId + ", itemQuantity="
				+ itemQuantity + ", discount=" + discount + ", totalAmount="
				+ totalAmount + ", finalAmount=" + finalAmount
				+ ", createDate=" + createDate + ", createUser=" + createUser
				+ ", bill=" + bill + ", item=" + item + "]";
	}
}

