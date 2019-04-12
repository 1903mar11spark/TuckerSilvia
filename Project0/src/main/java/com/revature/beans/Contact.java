package com.revature.beans;

public class Contact {

	private String phone;
	private BankUsers userId;
	private String email;
	private String address;
	private int zip;
	private String region;
	
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(String phone, BankUsers userId, String email, String address, int zip, String region) {
		super();
		this.phone = phone;
		this.userId = userId;
		this.email = email;
		this.address = address;
		this.zip = zip;
		this.region = region;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public BankUsers getUserId() {
		return userId;
	}
	public void setUserId(BankUsers userId) {
		this.userId = userId;
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
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "Contact [phone=" + phone + ", userId=" + userId + ", email=" + email + ", address=" + address + ", zip="
				+ zip + ", region=" + region + "]";
	}
	
	
}
