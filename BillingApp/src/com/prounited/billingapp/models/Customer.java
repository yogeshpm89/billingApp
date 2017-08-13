package com.prounited.billingapp.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	private long customerId;
	
	@Column(name = "first_name", unique = true, nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "birth_date", unique = true, nullable = true)
	private Date birthDate;
	 
	@Column(name = "last_name", unique = true, nullable = false, length = 20)
	private String lastName;
	
	@Column(name = "email", unique = true, nullable = true, length = 20)
	private String email;
	
	@Column(name = "address", unique = true, nullable = true, length = 20)
	private String address;
	
	@Column(name = "phone", unique = true, nullable = true, length = 13)
	private String phone;
	
	@Column(name = "is_active", unique = true, nullable = false, length = 1)
	private String isActive;
	
	@Column(name = "create_user", unique = true, nullable = false)
	private String createUser;
	
	@Column(name = "create_date", unique = true, nullable = false)
	private Date createDate;
	
	@Column(name = "update_user", unique = true, nullable = false)
	private String updateUser;
	
	@Column(name = "update_date", unique = true, nullable = false)
	private Date updateDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Bill> bills = new HashSet<Bill>(0);
	
	public Customer() {
		
	}
	
	public Customer(long customerId, String firstName, Date birthDate,
			String lastName, String email, String address, String phone) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName="
				+ firstName + ", birthDate=" + birthDate + ", lastName="
				+ lastName + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", createUser=" + createUser
				+ ", createDate=" + createDate + ", updateUser=" + updateUser
				+ ", updateDate=" + updateDate + "]";
	}

}
