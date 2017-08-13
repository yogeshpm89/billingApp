package com.prounited.billingapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private long userId;
	
	@Column(name = "user_name", unique = true, nullable = false, length = 20)
	private String userName;
	
	@Column(name = "password", unique = true, nullable = false, length = 20)
	private String password;
	
	@Column(name = "email", unique = true, nullable = false, length = 20)
	private String email;
	
	@Column(name = "address", unique = true, nullable = false, length = 20)
	private String address;
	
	@Column(name = "create_user", unique = true, nullable = false)
	private String createUser;
	
	@Column(name = "create_date", unique = true, nullable = false)
	private Date createDate;
	
	@Column(name = "update_user", unique = true, nullable = false)
	private String updateUser;
	
	@Column(name = "update_date", unique = true, nullable = false)
	private Date updateDate;

	public long getUserId() {
		return userId;
	}

	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", unique = true, nullable = false, length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_date", unique = true, nullable = false)
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


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + ", address="
				+ address + ", createUser=" + createUser + ", createDate="
				+ createDate + ", updateUser=" + updateUser + "]";
	}


	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


}
