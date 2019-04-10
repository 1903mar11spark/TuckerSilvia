package com.revature.beans;

public class BankUsers {

	private int userId;
	private String userName;
	private String password;
	private int userType;
	private String firstName;
	private String lastName;
	
	
	public BankUsers(int userId, String userName, String password, int userType, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public BankUsers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "BankUsers [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userType="
				+ userType + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
