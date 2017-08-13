package com.prounited.billingapp.vos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.BillItem;

public class BillVO {

	private long billId;
	private long billNumber;
	private float amount;
	private long customerId;
	private float discount;
	private float taxPercentage;
	private float taxAmount;
	private float finalAmount;
	private Date createDate;
	private String createUser;
	private String isActive;
	private CustomerVO customerVO;
	private Set<BillItemVO> billItemVOs = new HashSet<BillItemVO>(0);
	
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
	
	public CustomerVO getCustomerVO() {
		return customerVO;
	}

	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}

	public Set<BillItemVO> getBillItemVOs() {
		return billItemVOs;
	}

	public void setBillItemVOs(Set<BillItemVO> billItemVOs) {
		this.billItemVOs = billItemVOs;
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
	
	public static BillVO getBillVO(Bill bill) {
		BillVO billVO = new BillVO();
		billVO.setBillId(bill.getBillId());
		billVO.setBillNumber(bill.getBillNumber());
		billVO.setAmount(bill.getAmount());
		billVO.setTaxAmount(bill.getTaxAmount());
		billVO.setTaxPercentage(bill.getTaxPercentage());
		billVO.setDiscount(bill.getDiscount());
		billVO.setFinalAmount(bill.getFinalAmount());
		billVO.setCustomerId(bill.getCustomerId());
		billVO.setCreateDate(bill.getCreateDate());
		billVO.setCreateUser(bill.getCreateUser());
		billVO.setIsActive(bill.getIsActive());
		
		Set<BillItem> billItems = bill.getBillItems();
		for (BillItem billItem: billItems) {
			billVO.getBillItemVOs().add(BillItemVO.getBillItemVO(billItem));
		}
		
		billVO.setCustomerVO(CustomerVO.getCustomerVO(bill.getCustomer()));
		return billVO;
	}
	
	public static List<BillVO> getBillVOs(List<Bill> bills) {
		List<BillVO> billVOs = new ArrayList<BillVO>();
		for (Bill bill: bills) {
			billVOs.add(getBillVO(bill));
		}
		return billVOs;
	}

}
