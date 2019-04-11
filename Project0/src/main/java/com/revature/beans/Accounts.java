package com.revature.beans;

public class Accounts {

	private int accountID;
	private BankUsers user;
	private double balance;
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Accounts(int accountID, BankUsers user, double balance) {
		super();
		this.accountID = accountID;
		this.user = user;
		this.balance = balance;
	}
	public Accounts(int accountID, double balance) {
		super();
		this.accountID = accountID;
		this.balance = balance;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public BankUsers getUser() {
		return user;
	}
	public void setUser(BankUsers user) {
		this.user = user;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Accounts [accountID=" + accountID + ", user=" + user + ", balance=" + balance + "]";
	}
	
	
}
