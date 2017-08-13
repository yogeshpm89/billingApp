package com.prounited.billingapp.vos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.prounited.billingapp.models.Bill;
import com.prounited.billingapp.models.Category;
import com.prounited.billingapp.models.Customer;

public class CustomerVO {

	private long customerId;
	private String firstName;
	private Date birthDate;
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private long shoppingAmount;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public long getShoppingAmount() {
		return shoppingAmount;
	}
	
	public void setShoppingAmount(long shoppingAmount) {
		this.shoppingAmount = shoppingAmount;
	}
	public static CustomerVO getCustomerVO(Customer customer) {
		CustomerVO customerVO = new CustomerVO();

		customerVO.setCustomerId(customer.getCustomerId());
		customerVO.setFirstName(customer.getFirstName());
		customerVO.setLastName(customer.getLastName());
		customerVO.setBirthDate(customer.getBirthDate());
		customerVO.setAddress(customer.getAddress());
		customerVO.setPhone(customer.getPhone());
		customerVO.setEmail(customer.getEmail());
		
		Set<Bill> bills = customer.getBills();
		long shoppingAmount = 0l;
		for (Bill bill: bills) {
			if (bill.getIsActive() != null && "Y".equalsIgnoreCase(bill.getIsActive().toUpperCase())) {
				shoppingAmount = (long) (shoppingAmount + bill.getFinalAmount());
				customerVO.setShoppingAmount(shoppingAmount);
			}
		}
		return customerVO;
	}
	
	public static List<CustomerVO> getCustomerVOs(List<Customer> customers) {
		List<CustomerVO> customerVOs = new ArrayList<CustomerVO>();
		for (Customer customer: customers) {
			customerVOs.add(getCustomerVO(customer));
		}
		return customerVOs;
	}
}
