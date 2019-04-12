package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.BankUsers;
import com.revature.beans.Transactions;
import com.revature.beans.Accounts;

import com.revature.util.ConnectionUtil;

public class BankDAOImpl implements BankDAO {

	public List<BankUsers> getUsers(){
		List<BankUsers> b1 = new ArrayList<BankUsers>();
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT BANK_USERS.USER_ID, BANK_USERS.USER_NAME, BANK_USERS.PASWORD, BANK_USERS.USER_TYPE, "
					+ "BANK_USERS.FIRST_NAME, BANK_USERS.LAST_NAME FROM BANK_USERS";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int userId = rs.getInt("User_Id");
				String userName = rs.getString("User_Name");
				String password = rs.getString("Pasword");
				int userType = rs.getInt("User_Type");
				String firstName = rs.getString("First_Name");
				String lastName = rs.getString("Last_Name");
				b1.add(new BankUsers (userId, userName, password, userType, firstName, lastName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b1;
	}


	public boolean login(String user, String pass) {
	
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT BANK_USERS.PASWORD FROM BANK_USERS WHERE USER_NAME=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String Cpass = rs.getString("PASWORD");
				 if (pass.equals(Cpass)) {
					 return true;
				 }
      }
      } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return false;
	}
	
	public int getUserId(String user) {
		int userId=0;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT BANK_USERS.USER_ID "
					+ "FROM BANK_USERS WHERE USER_NAME = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("USER_ID");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userId;
	}
	
	public List<Accounts> getAccounts(int userId) {
		List<Accounts> a1 = new ArrayList<Accounts>();
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT ACCOUNTS.ACCOUNT_ID ,ACCOUNTS.BALANCE "
					+ "FROM ACCOUNTS WHERE USER_ID = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int accountId = rs.getInt("ACCOUNT_ID");
				double balance = rs.getInt("BALANCE");
				a1.add(new Accounts (accountId, balance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a1;
		
	}
	
	
	public int privileges(int userID) {
		int priv=0;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT BANK_USERS.USER_TYPE "
					+ "FROM BANK_USERS WHERE USER_ID = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				priv= rs.getInt("USER_TYPE");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return priv;
	}
	
	
	public void newUser(String user, String pass, String fName, String lName) {
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "{call SP_NEW_USER(?,?,?,?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, user);
			cs.setString(2, pass);
			cs.setString(3, fName);
			cs.setString(4, lName);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double getBalance(int userId, int account) {
		double balance=0;
		boolean exists = existingAccount(account);
		if(exists) {
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT ACCOUNTS.BALANCE "
					+ "FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_ID = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, account);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				balance = rs.getDouble("BALANCE");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return balance;
		}else {
			System.out.println("Account does not exists");
			System.out.println(" ");
			return -1;
		}
	}
	
	public double getBalanceDeposit(int account,int userId) {
		double balance=0;
		boolean exists = existingAccount(account);
		if(exists) {
			try (Connection con = ConnectionUtil.getConnection()){
				String sql = "SELECT ACCOUNTS.BALANCE "
						+ "FROM ACCOUNTS WHERE ACCOUNT_ID = ? AND USER_ID = ?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, account);
				stmt.setInt(2, userId);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
				while (rs.next()) {
					balance = rs.getDouble("BALANCE");
				}
			}
			else {
				return balance = -1;
				}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return balance;
		}else {
			System.out.println("Account does not exists");
			System.out.println(" ");
			return -1;
		}
	}
	
	public void updateBalance(int account, double balance) {
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "UPDATE ACCOUNTS "
					+ "SET BALANCE = ? WHERE ACCOUNT_ID = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, balance);
			stmt.setInt(2, account);
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void deleteAccount(int accountId) {
	
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "DELETE "
					+ "FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, accountId);
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean existingAccount(int accountId) {
		boolean exists = false;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT ACCOUNTS.ACCOUNT_ID "
					+ "FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, accountId);
			ResultSet rs = stmt.executeQuery();
			exists = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return exists;

	}
	public void newTransaction(String type, double amt, String date, int accountId) {
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO TRANSACTIONS (TRANSACTION_TYPE, TRANSACTION_AMT, TRANSACTION_DATE, ACCOUNT_ID)"
					+ " VALUES(?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, type);
			stmt.setDouble(2, amt);
			stmt.setString(3, date);
			stmt.setInt(4, accountId);
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}


	public void createAccount(int userId) {
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "{call SP_NEW_ACCOUNT(?)}";
			CallableStatement cs = con.prepareCall(sql);
			cs.setInt(1, userId);
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	 public List<Transactions>getTransactions(int account){
		 List<Transactions> t1 = new ArrayList<Transactions>();
			try (Connection con = ConnectionUtil.getConnection()){
				String sql = "SELECT TRANSACTIONS.TRANSACTION_ID, TRANSACTIONS.TRANSACTION_TYPE, TRANSACTIONS.TRANSACTION_AMT, TRANSACTIONS.TRANSACTION_DATE,TRANSACTIONS.ACCOUNT_ID"	
						+ " FROM TRANSACTIONS WHERE ACCOUNT_ID = ?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1,account);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int transactionId = rs.getInt("TRANSACTION_ID");
					String transactionType = rs.getString("TRANSACTION_TYPE");
					double transactionAmt = rs.getDouble("TRANSACTION_AMT");
					String transactionDate = rs.getString("TRANSACTION_DATE");
					int accountId = rs.getInt("ACCOUNT_ID");
					t1.add(new Transactions (transactionId, transactionType, transactionAmt,transactionDate,account));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return t1;
	 }

	 public void updateUser(String user, String pass, String fName, String lName, int userId) {
		 try (Connection con = ConnectionUtil.getConnection()){
				String sql = "{call SP_UPDATE_ACCOUNT(?,?,?,?,?)}";
				CallableStatement cs = con.prepareCall(sql);
				cs.setString(1, user);
				cs.setString(2, pass);
				cs.setString(3, fName);
				cs.setString(4, lName);
				cs.setInt(5, userId);
				cs.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	
	 public void deleteUsers() {
			try (Connection con = ConnectionUtil.getConnection()){
				String sql = "SELECT USER_ID FROM BANK_USERS";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					int userId = rs.getInt("USER_ID");
					try {
						String sql2 = "DELETE "
								+ "FROM BANK_USERS WHERE USER_ID = ?";
						PreparedStatement stmt2 = con.prepareStatement(sql2);
						stmt2.setInt(1, userId);
						stmt2.executeQuery();
					}catch(Error e) {
						e.printStackTrace();
						continue;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }	
}
