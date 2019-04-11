package com.revature.dao;

import java.util.List;

import com.revature.beans.BankUsers;
import com.revature.beans.Accounts;


public interface BankDAO {

	public List<BankUsers> getUsers();
	public boolean login(String user, String pass);
	public int getUserId(String user);
	public List<Accounts> getAccounts(int userId);
	public int privileges(int userID);
	public void newUser(String user, String pass, String fName, String lName);
	public void createAccount(int userId);
    public void deleteAllUsers();

	public double getBalance(int userId, int account);
    public void updateBalance(int account, double balance);
    public void deleteAccount(int userId);
    public boolean existingAccount(int accountId);

    public void newTransaction(int type, double amt, int accountId);

}

//need a create account method

//would it be easier to create an object that holds all of an users/accounts data instead of sending a new request to the DB every time?