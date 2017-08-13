package com.prounited.billingapp.vos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.prounited.billingapp.models.BillItem;
import com.prounited.billingapp.models.Category;
import com.prounited.billingapp.models.Item;

public class BillItemVO {

	private long billItemId;
	private long billId;
	private long itemId;
	private long itemQuantity;
	private float discount;
	private float totalAmount;
	private float finalAmount;
	private Date createDate;
	private String createUser;
	
	private ItemVO itemVO;

	public long getBillItemId() {
		return billItemId;
	}

	public void setBillItemId(long billItemId) {
		this.billItemId = billItemId;
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

	public ItemVO getItemVO() {
		return itemVO;
	}

	public void setItemVO(ItemVO itemVO) {
		this.itemVO = itemVO;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "BillItem [billItemId=" + billItemId + ", billId=" + billId
				+ ", itemid=" + itemId + ", itemQuantity=" + itemQuantity
				+ ", discount=" + discount + ", totalAmount=" + totalAmount
				+ ", finalAmount=" + finalAmount + ", createDate=" + createDate
				+ ", createUser=" + createUser + "]";
	}
	
	public static BillItemVO getBillItemVO(BillItem  billItem) {
		BillItemVO billItemVO = new BillItemVO();
		
		billItemVO.setBillId(billItem.getBillId());
		billItemVO.setBillItemId(billItem.getBillItemId());
		billItemVO.setDiscount(billItem.getDiscount());
		billItemVO.setFinalAmount(billItem.getFinalAmount());
		billItemVO.setItemQuantity(billItem.getItemQuantity());
		billItemVO.setTotalAmount(billItem.getTotalAmount());
		billItemVO.setItemVO(ItemVO.getItemVO(billItem.getItem()));
		return billItemVO;
	}
	
	public static List<BillItemVO> getBillItemVOs(List<BillItem> billItems) {
		List<BillItemVO> billItemVOs = new ArrayList<BillItemVO>();
		for (BillItem billItem: billItems) {
			billItemVOs.add(getBillItemVO(billItem));
		}
		return billItemVOs;
	}
	
}

