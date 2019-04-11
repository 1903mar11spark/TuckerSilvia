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

	//this should be a PreparedStatement!
	public String checkLogin(String user) {
		String pass = "none found";
		try(Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT BANK_USERS.PASWORD FROM BANK_USERS WHERE USER_NAME=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				 pass = rs.getString("PASWORD");
      }
      } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return pass;
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
			String sql = "SELECT ACCOUNTS.ACCOUNT_ID, ACCOUNTS.BALANCE "
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
	
	public boolean login(String user, String pass) {
		
		
		return true;
	}
	
	
	public int privileges(String user, String pass) {
		int priv=0;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "SELECT BANK_USERS.USER_TYPE "
					+ "FROM BANK_USERS WHERE USER_NAME = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user);
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
		
	}
	
	public double getBalance(int userId, String Account) {
		return 0.0;
	}
	
	public void updateBalance(String account, double balance) {
		
	}
	//accounts must be empty before deleted
	public void deleteAccount(String user, String account) {
		
	}
	
	//what about their accounts? just the empty accounts? and their contact information?
	
	//DO NOT RUN THIS, IT MAY ACTUALLY WORK
	public void deleteAllUsers() {
		
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "DELETE * "
					+ "FROM BANK_USERS";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				rs.deleteRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
