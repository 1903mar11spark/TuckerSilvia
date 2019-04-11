package com.revature.beans;

public class Transactions {

	private int transactionId;
	private String tType;
	private double tAmount;
	private String tDate;
	private Accounts accounts;
	private int accountss;
	
	
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transactions(int transactionId, String tType, double tAmount, String tDate, Accounts accounts) {
		super();
		this.transactionId = transactionId;
		this.tType = tType;
		this.tAmount = tAmount;
		this.tDate = tDate;
		this.accounts = accounts;
	}
	public Transactions(int transactionId, String tType, double tAmount, String tDate, int acountss) {
		this.transactionId = transactionId;
		this.tType = tType;
		this.tAmount = tAmount;
		this.tDate = tDate;
		this.accountss = accountss;
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
	public double gettAmount() {
		return tAmount;
	}
	public void settAmount(double tAmount) {
		this.tAmount = tAmount;
	}
	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}
	public Accounts getAccounts() {
		return accounts;
	}
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	public int getAccountss() {
		return accountss;
	}
	public void setAccountss(int accountss) {
		this.accountss = accountss;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "Transactions [Transaction Id= " + transactionId + ", Transaction Type= " + tType + ", Transaction Amount=" + tAmount
				+", Date:" + tDate + ", Account =" + accountss + "]";
	}
	
	
}
