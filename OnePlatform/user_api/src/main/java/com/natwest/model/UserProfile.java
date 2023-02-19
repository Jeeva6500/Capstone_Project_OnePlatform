package com.natwest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userprofile")
public class UserProfile {
	
	@Id
	private String customerId;
	
	private String phone;
	private String name;
	private String email;
	private String pan;
	private String password;
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProfile(String customerId, String phone, String name, String email, String pan, String password) {
		super();
		this.customerId = customerId;
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.pan = pan;
		this.password = password;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserProfile [customerId=" + customerId + ", phone=" + phone + ", name=" + name + ", email=" + email
				+ ", pan=" + pan + ", password=" + password + "]";
	}

	
		

}