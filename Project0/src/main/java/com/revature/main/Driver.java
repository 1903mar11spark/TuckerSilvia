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
		BankDAO bd = new BankDAOImpl();
		/*List<BankUsers> users = bd.getUsers();
		for (BankUsers b:users) {
			System.out.println(b);
		}*/
		
		Scanner uInput = new Scanner(System.in);
		System.out.println("Welcome, please input your user name: ");
		String user = uInput.nextLine();
		System.out.println(" please input your password: ");
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
					System.out.println("2 Update Contact Info");
					System.out.println("3 Delete Account");
					System.out.println("4 Withdraw from account");
					System.out.println("5 Deposit into Account");
					System.out.println("6 Transactions Log");
					System.out.println("7 Logout");
					System.out.println("********************************************************************");
					selection = uInput.nextInt();
					switch(selection) {
						case 1:
							List<Accounts> accounts = bd.getAccounts(userId);
							for (Accounts a:accounts) {
								System.out.println(a);
							}
							break;
						case 2:
							System.out.println("please input your phone number: ");
							String phone= uInput.nextLine();
							phone= uInput.nextLine();
							System.out.println("Please input your email: ");
							String email= uInput.nextLine();
							System.out.println("Please input your street address: ");
							String address= uInput.nextLine();
							System.out.println("Please input your zip code: ");
							int zip = uInput.nextInt();
							System.out.println("Please input your region: ");
							String region= uInput.nextLine();
							region= uInput.nextLine();
							bd.setContactInfo(phone, userId, email, address, zip, region);
							System.out.println("** Account succesfully Updated **");
							break;
						case 3:
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
								//account does not exists
							}else {
								System.out.println("Cannot delete account, current balance is: " + balance);
								System.out.println(" ");
							}
							break;
						case 4:
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
						case 5:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							System.out.println("Select amount to deposit: ");
							amount = uInput.nextDouble();
							balance = bd.getBalanceDeposit(account);
							balance = balance + amount;
							String type = "Deposit";
							String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
							bd.updateBalance(account, balance);
							bd.newTransaction(type,amount,date,account);
							System.out.println("Deposit has been succesfull on: " + date);
							System.out.println(" ");
							break;
						case 6:
							System.out.println("Select the account: ");
							account = uInput.nextInt();
							List<Transactions> transactions = bd.getTransactions(account);
							for (Transactions a:transactions) {
								System.out.println(a);
							}
							
							break;
						case 7:
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
					System.out.println("********************************************************************");
					System.out.println("Welcome: " + user + " Choose a Transaction");
					System.out.println("1 View Accounts");
					System.out.println("2 Create Account");
					System.out.println("3 Update Account");
					System.out.println("4 Delete all users");
					System.out.println("5 Update User Contact Info");
					System.out.println("6 Logout");
					System.out.println("********************************************************************");
					selection = uInput.nextInt();
					switch(selection) {
						case 1:
							List<Accounts> accounts = bd.getAccounts(userId);
							for (Accounts a:accounts) {
								System.out.println(a);
							}
							break;
						case 2:
							System.out.println("Enter User_Id of new account holder");
							selection = uInput.nextInt();
							bd.createAccount(selection);
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							System.out.println("Please type user id to modify: ");
							int userIdUpdate= uInput.nextInt();
							System.out.println("please input your phone number: ");
							String phone= uInput.nextLine();
							phone= uInput.nextLine();
							System.out.println("Please input your email: ");
							String email= uInput.nextLine();
							System.out.println("Please input your street address: ");
							String address= uInput.nextLine();
							System.out.println("Please input your zip code: ");
							int zip = uInput.nextInt();
							System.out.println("Please input your region: ");
							String region= uInput.nextLine();
							region= uInput.nextLine();
							bd.setContactInfo(phone, userIdUpdate, email, address, zip, region);
							System.out.println("** Account succesfully Updated **");
							break;
						case 6:
							
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

				newUser = uInput.nextLine();

				System.out.println(" please input your password: ");
				String newPass= uInput.nextLine();
				System.out.println("Please input your first name: ");
				String fName = uInput.nextLine();
				System.out.println(" please input your last name: ");
				String lName= uInput.nextLine();
				System.out.println(" please input your phone number: ");
				String phone= uInput.nextLine();
				System.out.println(" please input your email: ");
				String email= uInput.nextLine();
				System.out.println(" please input your street address: ");
				String address= uInput.nextLine();
				System.out.println(" please input your zip code: ");
				int zip = uInput.nextInt();
				System.out.println(" please input your region: ");
				String region= uInput.nextLine();
				region= uInput.nextLine();
				bd.newUser(newUser, newPass, fName, lName);
				int userIdCheck = bd.getUserId(newUser);
				bd.setContactInfo(phone, userIdCheck, email, address, zip, region);
				System.out.println("** Account succesfully created **");
				System.out.println("** Your user name is:  " + newUser + " UserId is: " + userIdCheck +" **");
				System.out.println(" ");
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
	
}

