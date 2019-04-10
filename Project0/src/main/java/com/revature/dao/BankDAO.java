package com.revature.dao;

import java.util.List;

import com.revature.beans.BankUsers;
import com.revature.beans.Accounts;


public interface BankDAO {

	public List<BankUsers> getUsers();
	public String checkLogin(String user);
	public int getUserId(String user);
	public List<Accounts> getAccounts(int userId);
	public boolean login(String user, String pass);
	public int privileges(String user, String pass);
	public void newUser(String user, String pass, String fName, String lName);
	public double getBalance(int userId, String Account);
    public void updateBalance(String account, double balance);
    public void deleteAccount(String user, String account);
}
