package com.revature.main;


import java.text.SimpleDateFormat;
import java.util.*;
import com.revature.beans.BankUsers;
import com.revature.beans.Transactions;
import com.revature.beans.Accounts;
import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;

public class Driver {
	
	public static void main(String [] args) {
		
		screen();
	}
	
	public static void screen() {
	BankDAO bd = new BankDAOImpl();
		
		Scanner uInput = new Scanner(System.in);
		System.out.println("Welcome, please input your user name: ");
		String user = uInput.nextLine();
		System.out.println("Please input your password: ");
		String pass = uInput.nextLine();
		int selection;
		boolean exists;
		exists = bd.login(user,pass);
		int userId;
		userId = bd.getUserId(user);
		boolean session = true;
		int account;
		double balance;
		double amount;
		if (exists) {
			int privilege = bd.privileges(userId);
			switch(privilege) {
			case 0:
				while (session) {
					System.out.println("********************************************************************");
					System.out.println("Welcome: " + user + " Choose a Transaction");
					System.out.println("1 View Accounts and balance");
					System.out.println("2 Delete Account");
					System.out.println("3 Withdraw from account");
					System.out.println("4 Deposit into Account");
					System.out.println("5 Transactions Log");
					System.out.println("6 Logout");
					System.out.println("********************************************************************");
					selection = uInput.nextInt();
					switch(selection) {
						case 1:
							List<Accounts> accounts = bd.getAccounts(userId);
							System.out.println("Accounts: ");
							for (Accounts a:accounts) {
								System.out.println(a);
							}
							break;
						case 2:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							System.out.println("Getting account information...");
							balance = bd.getBalance(userId, account);
							System.out.println("Current balance: " + balance);
							System.out.println(" ");
							if(balance == 0.0) {
								System.out.println("Delete account? 1 Yes 2 No");
								selection = uInput.nextInt();
								if(selection ==1) {
									bd.deleteAccount(account);
									System.out.println("Account was deleted...");
									System.out.println(" ");
								}else if (selection ==2) {
									System.out.println("Account not deleted");
									System.out.println(" ");
								}else {
									System.out.println("Wrong entry");
									System.out.println(" ");
								}
							}else if(balance == -1){
								System.out.println("Account does not exist");
							}else {
								System.out.println("Cannot delete account, current balance is: " + balance);
								System.out.println(" ");
							}
							break;
						case 3:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							System.out.println("Select amount to withdraw: ");
							amount = uInput.nextDouble();
							balance = bd.getBalance(userId, account);
							if(balance > amount) {
								balance = balance - amount;
								String type = "Withdrawal";
								String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
								bd.updateBalance(account, balance);
								bd.newTransaction(type, balance,date,account);
								System.out.println("Please take your cash ");
								System.out.println(" ");
							}else {
								System.out.println("Not enough balance to perform this transaction...");
								System.out.println(" ");
							}
							break;
						case 4:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							System.out.println("Select amount to deposit: ");
							amount = uInput.nextDouble();
							balance = bd.getBalanceDeposit(account,userId);
							if(balance>0) {
								balance = balance + amount;
								String type = "Deposit";
								String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
								bd.updateBalance(account, balance);
								bd.newTransaction(type,amount,date,account);
								System.out.println("Deposit has been succesfull on: " + date);
								System.out.println(" ");}
							else {
								System.out.println("Invalid account ID");
							}
							break;
						case 5:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							List<Transactions> transactions = bd.getTransactions(account);
							System.out.println("Transaction History: ");
							for (Transactions a:transactions) {
								System.out.println(a);
							}
							
							break;
						case 6:
							System.out.println("Log out? 1 Yes 2 No");
							selection = uInput.nextInt();
							if(selection ==1) {
								user = "";
								pass = "";
								System.out.println("Thank you for using our services, Have a nice day!");
								System.out.println(" ");
								screen();
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
					System.out.println("********************************************************************");
					System.out.println("Welcome: " + user + " Choose a Transaction");
					System.out.println("1 View Accounts");
					System.out.println("2 Withdraw from account");
					System.out.println("3 Deposit into Account");
					System.out.println("4 Transactions Log");
					System.out.println("5 Create Account");
					System.out.println("6 Update Account");
					System.out.println("7 Delete all users");
					System.out.println("8 Logout");
					System.out.println("********************************************************************");
					selection = uInput.nextInt();
					switch(selection) {
						case 1:
							List<Accounts> accounts = bd.getAccounts(userId);
							System.out.println("Accounts: ");
							for (Accounts a:accounts) {
								System.out.println(a);
							}
							break;
						case 2:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							System.out.println("Getting account information...");
							balance = bd.getBalance(userId, account);
							System.out.println("Current balance: " + balance);
							System.out.println(" ");
							if(balance == 0.0) {
								System.out.println("Delete account? 1 Yes 2 No");
								selection = uInput.nextInt();
								if(selection ==1) {
									bd.deleteAccount(account);
									System.out.println("Account was deleted...");
									System.out.println(" ");
								}else if (selection ==2) {
									System.out.println("Account not deleted");
									System.out.println(" ");
								}else {
									System.out.println("Wrong entry");
									System.out.println(" ");
								}
							}else if(balance == -1){
								System.out.println("Account does not exist");
							}else {
								System.out.println("Cannot delete account, current balance is: " + balance);
								System.out.println(" ");
							}
							break;
						case 3:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							System.out.println("Select amount to withdraw: ");
							amount = uInput.nextDouble();
							balance = bd.getBalance(userId, account);
							if(balance > amount) {
								balance = balance - amount;
								String type = "Withdrawal";
								String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
								bd.updateBalance(account, balance);
								bd.newTransaction(type, balance,date,account);
								System.out.println("Please take your cash ");
								System.out.println(" ");
							}else {
								System.out.println("Not enough balance to perform this transaction...");
								System.out.println(" ");
							}
							break;
						case 4:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							List<Transactions> transactions = bd.getTransactions(account);
							System.out.println("Transaction History: ");
							for (Transactions a:transactions) {
								System.out.println(a);
							}
							break;
						case 5:
							System.out.println("Enter User_Id of new account holder");
							selection = uInput.nextInt();
							bd.createAccount(selection);
							System.out.println("Account Created");
							break;
						case 6:
							System.out.println("Enter User_Id of account ");
							int nUserId = uInput.nextInt();
							System.out.println("Please input new user name: ");
							String nUser = uInput.nextLine();
							nUser = uInput.nextLine();
							System.out.println("Please input new password: ");
							String nPass= uInput.nextLine();
							System.out.println("Please re-enter password: ");
							String NPass= uInput.nextLine();
							if (nPass.equals(NPass)) {
							System.out.println("Please input first name: ");
							String nFName = uInput.nextLine();
							System.out.println("Please input last name: ");
							String nLName= uInput.nextLine();
							bd.updateUser(nUser, nPass, nFName, nLName,  nUserId);
							}else {
								System.out.println("Passwords did not match");
							}
							break;
						case 7:
							System.out.println("Are you sure you want to delete all users? 1 Yes 2 No ");
							int response = uInput.nextInt();
							switch (response) {
							case 1:
								System.out.println("Seriously this will delete ALL the users? 1 Yes 2 No ");
								int response2 = uInput.nextInt();
								switch (response2) {
								case 1:
									System.out.println("Take out word for it, it works! 1 Yes 2 No ");
									int response3 = uInput.nextInt();
									switch (response3) {
									case 1:
										bd.deleteUsers();
										break;
									case 2:
										break;
									}
								case 2:
									break;
								}
								break;
							case 2:
								break;
							}System.out.println("*snap*");
							break;
						case 8:
							System.out.println("Log out? 1 Yes 2 No");
							selection = uInput.nextInt();
							if(selection ==1) {
								user = "";
								pass = "";
								System.out.println("Thank you for using our services, Have a nice day!");
								screen();
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
				newUser = uInput.nextLine();
				System.out.println(" please input your password: ");
				String newPass= uInput.nextLine();
				System.out.println("Please input your first name: ");
				String fName = uInput.nextLine();
				System.out.println(" please input your last name: ");
				String lName= uInput.nextLine();
				bd.newUser(newUser, newPass, fName, lName);
				System.out.println("Thank you for creating an user account, please log in");
				break;
			case 2:
				System.out.println("Have a nice day");
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}System.out.println(" ");
			screen();
		}
		uInput.close();
	}
	
}

