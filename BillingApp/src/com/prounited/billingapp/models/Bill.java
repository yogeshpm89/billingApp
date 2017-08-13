package com.prounited.billingapp.models;

import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bills")
public class Bill implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id", unique = true, nullable = false)
	private long billId;
	
	@Column(name = "bill_number", nullable = false)
	private long billNumber;
	
	@Column(name = "amount", nullable = false)
	private float amount;
	
	@Column(name = "discount", nullable = false)
	private float discount;
	
	@Column(name = "tax_percentage", nullable = false)
	private float taxPercentage;
	
	@Column(name = "tax_amount", nullable = false)
	private float taxAmount;
	
	@Column(name = "final_amount", nullable = false)
	private float finalAmount;
	
	@Column(name = "customer_id", nullable = false)
	private long customerId;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	@Column(name = "create_user", nullable = false, length = 20)
	private String createUser;

	@Column(name = "is_active", unique = true, nullable = false, length = 1)
	private String isActive;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
	private Set<BillItem> billItems = new HashSet<BillItem>(0);

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false, insertable= false, updatable=false)
	private Customer customer;
	
	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public long getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(long billNumber) {
		this.billNumber = billNumber;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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
	
	public Set<BillItem> getBillItems() {
		return billItems;
	}

	public void setBillItems(Set<BillItem> billItems) {
		this.billItems = billItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(float taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public float getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(float taxAmount) {
		this.taxAmount = taxAmount;
	}

	public float getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(float finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", amount=" + amount
				+ ", customerId=" + customerId + ", createDate=" + createDate
				+ ", createUser=" + createUser + "]";
	}
	
	public String getCustomerName() {
		return this.getCustomer().getFirstName() + "  " + this.getCustomer().getLastName(); 
	}
	
	public String getCustomerAddress() {
		return this.getCustomer().getAddress(); 
	}
}
