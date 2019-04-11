package com.revature.dao;

import java.util.List;

import com.revature.beans.BankUsers;
import com.revature.beans.Accounts;


public interface BankDAO {

	public List<BankUsers> getUsers();
	public String checkLogin(String user);
	public int getUserId(String user);
	public List<Accounts> getAccounts(int userId);
	public int privileges(String user, String pass);
	public void newUser(String user, String pass, String fName, String lName);

    public void deleteAllUsers();

	public double getBalance(int userId, int account);
    public void updateBalance(int account, double balance);
    public void deleteAccount(int userId);
    public boolean existingAccount(int accountId);

}


//would it be easier to create an object that holds all of an users/accounts data instead of sending a new request to the DB every time?