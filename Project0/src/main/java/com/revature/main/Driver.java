package com.revature.main;

import java.util.*;
import com.revature.beans.BankUsers;
import com.revature.beans.Accounts;
import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;

public class Driver {
	
	public static void main(String [] args) {
		BankDAO bd = new BankDAOImpl();
		List<BankUsers> users = bd.getUsers();
		for (BankUsers b:users) {
			System.out.println(b);
		}
		
		Scanner uInput = new Scanner(System.in);
		System.out.println("Welcome, please input your user name: ");
		String user = uInput.nextLine();
		System.out.println(" please input your password: ");
		String pass= uInput.nextLine();
		int selection;
		boolean exists;

		exists = login(user,pass);
		
		System.out.println( login(user, pass));

		
		exists = bd.login(user, pass);
		int userId;
		userId = bd.getUserId(user);
		boolean session = true;
		String account;
		double balance;
		double amount;
		if (exists) {
			int privilege = bd.privileges(user, pass);
			switch(privilege) {
			case 0:

				System.out.println("Welcome: " + user + " Choose a Transaction");
				System.out.println("1 View Accounts and balance");
				System.out.println("2 Delete Account");
				System.out.println("3 Withdraw from account");
				System.out.println("4 Deposit into Account");
				System.out.println("5 Logout");
				selection = uInput.nextInt();
				switch(selection) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid entry");
					break;
				}

				while (session) {
					System.out.println("Welcome: " + user + " Choose a Transaction");
					System.out.println("1 View Accounts and balance");
					System.out.println("2 Delete Account");
					System.out.println("3 Withdraw from account");
					System.out.println("4 Deposit into Account");
					System.out.println("5 Logout");
					selection = uInput.nextInt();
					switch(selection) {
						case 1:
							List<Accounts> accounts = bd.getAccounts(userId);
							for (Accounts a:accounts) {
								System.out.println(a);
							}
							break;
						case 2:
							System.out.println("Select the account: ");
							account = uInput.nextLine();
							System.out.println("Getting account information...");
							balance = bd.getBalance(userId, account);
							if(balance == 0.0) {
								System.out.println("Delete account? 1 Yes 2 No");
								selection = uInput.nextInt();
								if(selection ==1) {
									bd.deleteAccount(user,account);
									System.out.println("Account was deleted...");
								}else if (selection ==2) {
									System.out.println("Account not deleted2");
								}else {
									System.out.println("Wrong entry");
								}
								
							}else {
								System.out.println("Cannot delete account, current balance is: " + balance);
							}
							break;
						case 3:
							System.out.println("Select the account: ");
							account = uInput.nextLine();
							System.out.println("Select amount to withdraw: ");
							amount = uInput.nextInt();
							balance = bd.getBalance(userId, account);
							if(balance > amount) {
								balance = balance - amount;
								bd.updateBalance(account, balance);
							}else {
								System.out.println("Not enough balance to perform this transaction...");
							}
							break;
						case 4:
							System.out.println("Select the account: ");
							account = uInput.nextLine();
							System.out.println("Select amount to withdraw: ");
							amount = uInput.nextInt();
							balance = bd.getBalance(userId, account);
							balance = balance + amount;
							bd.updateBalance(account, balance);
							break;
						case 5:
							System.out.println("Log out? 1 Yes 2 No");
							selection = uInput.nextInt();
							if(selection ==1) {
								user = "";
								pass = "";
								System.out.println("Thank you for using our services, Have a nice day!");
								session =false;
								break;
							}else if (selection ==2) {
								break;
							}else {
								System.out.println("Wrong entry");
								break;
							}
						default:
							System.out.println("Invalid entry");
							break;
					}
				} ;

				break;
			case 1:
				while (session) {
					System.out.println("Welcome: " + user + " Choose a Transaction");
					System.out.println("1 View Accounts");
					System.out.println("2 Create Account");
					System.out.println("3 Update Account");
					System.out.println("4 Delete all users");
					System.out.println("5 Logout");
					selection = uInput.nextInt();
					switch(selection) {
						case 1:
							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							
							System.out.println("Log out? 1 Yes 2 No");
							selection = uInput.nextInt();
							if(selection ==1) {
								user = "";
								pass = "";
								System.out.println("Thank you for using our services, Have a nice day!");
								session =false;
								break;
							}else if (selection ==2) {
								break;
							}else {
								System.out.println("Wrong entry");
								break;
							}
						default:
							System.out.println("Invalid entry");
							break;
					}
				};
				break;
			default:
				break;
			}
			
		}else {
			System.out.println("Your user was not found, would you like to create a new user? 1 Yes 2 No ");
			int response = uInput.nextInt();
			switch (response) {
			case 1:
				System.out.println("Please input your user name: ");
				String newUser = uInput.nextLine();
				System.out.println(" please input your password: ");
				String newPass= uInput.nextLine();
				System.out.println("Please input your first name: ");
				String fName = uInput.nextLine();
				System.out.println(" please input your last name: ");
				String lName= uInput.nextLine();
				bd.newUser(newUser, newPass, fName, lName);
				break;
			case 2:
				System.out.println("Have a nice day");
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}
		}
		uInput.close();
	}
	

	private static boolean login(String user, String pass) {
		BankDAO bd = new BankDAOImpl();
		String p = bd.checkLogin(user);
		System.out.println("password entered "+pass+" expected "+p);
		if (p.equals(pass)) {
			return true;
		}else
		return false;
	}

	
}

