package com.revature.beans;

public class Transactions {

	private int transactionId;
	private String tType;
	private int tAmount;
	private Accounts accounts;
	
	
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transactions(int transactionId, String tType, int tAmount, Accounts accounts) {
		super();
		this.transactionId = transactionId;
		this.tType = tType;
		this.tAmount = tAmount;
		this.accounts = accounts;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String gettType() {
		return tType;
	}
	public void settType(String tType) {
		this.tType = tType;
	}
	public int gettAmount() {
		return tAmount;
	}
	public void settAmount(int tAmount) {
		this.tAmount = tAmount;
	}
	public Accounts getAccounts() {
		return accounts;
	}
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", tType=" + tType + ", tAmount=" + tAmount
				+ ", accounts=" + accounts + "]";
	}
	
	
}
