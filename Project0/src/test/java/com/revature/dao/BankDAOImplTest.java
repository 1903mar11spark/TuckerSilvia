package com.revature.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.beans.Accounts;
import com.revature.beans.BankUsers;
import com.revature.beans.Transactions;

public class BankDAOImplTest {

	private static final BankDAOImpl bank = new BankDAOImpl();

	@Test
	public void testSuper() {
		assertTrue(bank.login("TheBoss","Password"));
	}
	@Test
	public void testReg() {
		assertTrue(bank.login("ClientOne","CPassw0rd"));
	}
	@Test
	public void testBadUser() {
		assertFalse(bank.login("TheBos","Password"));
	}
	@Test
	public void testBadPass() {
		assertTrue(bank.login("TheBoss","Passord"));
	}
	@Test
	public void testUser() {
		assertEquals(26, bank.getUserId("TheBoss"));
	}
	@Test
	public void testInvalid() {
		assertEquals(0, bank.getUserId("Theoss"));
	}
	@Test
	public void testPrivileges() {
		assertEquals(0,bank.privileges(28));
	}
	@Test
	public void testSuperPrivileges() {
		assertEquals(1 ,bank.privileges(27));
	}
	@Test
	public void testInvalidPrivileges() {
		assertEquals(0, bank.privileges(3));
	}
	@Test
	public void testBalance () {
		assertEquals(1000, bank.getBalance(26, 10));
	}

	
	@Test
	public void testExists() {
		assertTrue(bank.existingAccount(10));
	}
	@Test
	public void testDoesNotExists() {
		assertFalse(bank.existingAccount(1));
	}
	@Test
	public void testZeroNotExists() {
		assertFalse(bank.existingAccount(0));
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