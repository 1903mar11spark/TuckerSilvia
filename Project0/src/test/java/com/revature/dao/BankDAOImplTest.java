package com.revature.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.beans.Accounts;
import com.revature.beans.BankUsers;
import com.revature.beans.Transactions;

public class BankDAOImplTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void loginTest() {
		String use="TheBoss";
		String pas="Password";
		assertTrue(BankDAOImpl.login(use,pas));
	}

}


/*	public List<BankUsers> getUsers();
1. public boolean login(String user, String pass);
2. public int getUserId(String user);
3. public List<Accounts> getAccounts(int userId);
4. public int privileges(int userID);
	public void newUser(String user, String pass, String fName, String lName);
	public void createAccount(int userId);
5. public double getBalance(int userId, int account);
6. public double getBalanceDeposit(int account, int userId);
	public void updateBalance(int account, double balance);
	public void deleteAccount(int userId);
7. public boolean existingAccount(int accountId);
	public void newTransaction(String type, double amt,String date, int accountId);
8. public List<Transactions>getTransactions(int account);
	public void updateUser(String user, String pass, String fName, String lName, int userId);
	public void deleteUsers();
*/