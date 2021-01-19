package dev.felleman.app;

import java.util.List;
import java.util.Scanner;

import dev.felleman.daos.BankAccountDAO;
import dev.felleman.daos.BankAccountDAOImpl;
import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;
import dev.felleman.services.BankAccountServices;
import dev.felleman.services.BankAccountServicesImpl;
import dev.felleman.services.UserServices;
import dev.felleman.services.UserServicesImpl;

/**
 * The BankApp class will take the place of a user interface for this project 0. 
 * 
 * The main logic and flow control will be placed inside a main method in this class. 
 * 
 *
 * @author DanielFelleman
 *
 */
public class BankApp {
	
	// Scanner for collecting user input
	private static Scanner scanner = new Scanner(System.in);
	
	
	// Instantitate a DAO to track all changes
	private static UserDAO userDAO = new UserDAOImpl();
	
	
	// And a services impl for interacting with the accounts?
	private static UserServices uServ = new UserServicesImpl();
	
	
	// BankAccountDAO
	private static BankAccountDAO baDAO = new BankAccountDAOImpl();
	
	
	// BankAccountServices
	private static BankAccountServices baServ = new BankAccountServicesImpl();
	
	// Temp user object for if a user is logged in
	private static User sessionUser = null;
	
	
	
	public static void main(String[] args ) {
		
		// WELCOME MENU
		welcomeScreen();
		
		
	}
	
	/*
	 * 
	 *   -- METHODS --
	 *   
	 */
	
	public static void welcomeScreen() {
		String welcomeMessage = "\nWelcome to the Banking App!" + "\n"
				+ "Please choose an option to continue:" + "\n"
				+ "1. Log In - for registered users." + "\n"
				+ "2. Register New User" + "\n"
				+ "3. Quit Application";

		System.out.println(welcomeMessage);
		
		// Collect Input
		int choice = collectInput();
		
		// Parse Input
		parseWelcomeInput(choice);
	}
	
	public static int collectInput() {
		int choice = scanner.nextInt();
		return choice;
	}
	
	public static void parseWelcomeInput(int choice) {
		
		// make sure they've entered a valid option
		if (choice == 1 || choice == 2 || choice ==3) { 	
			switch(choice) {
			
			case 1: {
				loginScreen();
				break;
			}
			
			case 2: {
				createUserAccountScreen();
				break;
			}
			
			case 3: {
				System.out.println("Thank you for using our online banking application. See you soon!");
				break;
			}
		
			default: {
				// default implementation if input doesn't match any choices
				System.out.println("Uh oh! It looks like you entered an invalid character. \nPlease only select from the options provided.");
				welcomeScreen();
			}
				
			}
		} else {
			System.out.println("Uh oh! It looks like you entered an invalid character. \nPlease only select from the options provided.");
			welcomeScreen();
		}
		
	}
	
	public static void loginScreen() {
		// Ask for username and password
		System.out.println("\nYou've reached the Login Screen. Please login to continue.");
		System.out.println("Please enter your username: ");
		String username = scanner.next();
		
		// check to make sure username exists
		if (userDAO.getUserByUsername(username) == null) {
			System.out.println("Oops! It seems like that username doesn't exist. Please select Register New User.");
			welcomeScreen();
		}
		System.out.println("Please enter your password: ");
		String password = scanner.next();
		
		User user = userDAO.getUserByUsername(username);
		boolean success = uServ.login(user, username, password);
		if (success) {
			sessionUser = user;
			System.out.println("You are logged in as: " + sessionUser.getUsername());
		} else {
			System.out.println("Oops, it looks like that isn't the password associated with your account. Please try again.");
			loginScreen();
		}
		mainMenuScreen();
	}
	
	public static void mainMenuScreen() {
		String mainMenu = "\nHello, " + sessionUser.getFirstName() + ". Welcome to the Main Menu." + "\n"
						+ "\nPlease select from the below options:" + "\n"
						+ "\t1. View Bank Accounts and Balances" + "\n"
						+ "\t2. Open Bank Account" + "\n"
						+ "\t3. Complete a Transaction" + "\n"
						+ "\t4. Log Out" + "\n"
						+ "\t5. Admin Menu";// TODO 
		System.out.println(mainMenu);

		parseMenuInput(collectInput());
	}
	
	public static void parseMenuInput(int choice) {

		// make sure they've entered a valid input
			if (choice == 1 || choice == 2 || choice == 3) {
				switch (choice) {
				
					// view bank accounts
					case 1: {
						baServ.viewAllUserBankAccounts(sessionUser.getUserId());
						
						mainMenuScreen();
						break;
					}
					
					// create a bank account
					case 2: {
						baServ.openAccount(sessionUser);
						System.out.println("Congratulations. You now have a bank account with us.");
						System.out.println(baDAO.getBankAccountById(sessionUser.getUserId()));
						
						mainMenuScreen();
						break;
					}
					
					// make a transaction
					case 3: {
						
						transactionScreen();
						break;
					}
					
					default: {
					System.out.println("It seems you've either selected an option that doesn't exist, or that you don't have permission for.");
					System.out.println("Please only select from the options shown by entering a single number.");
					adminMenuScreen();
					break;
				}
				}
		
			}
			
			if (choice == 4) {
				uServ.logout(sessionUser);
				welcomeScreen();
			}
			
			if (choice == 5) {
				if (sessionUser.getIsSuper() == 1) {
					adminMenuScreen();
				} else {
					System.out.println("Oops! It looks like you are not an administrator. You don't have access to that menu.");
					mainMenuScreen();
				}
			}
			else {
				System.out.println("It seems you've either selected an option that doesn't exist, or that you don't have permission for.");
				System.out.println("Please only select from the options shown by entering a single number.");
				mainMenuScreen();
			}
			
	}
	
	public static void adminMenuScreen() {
		String adminMenu = "\nHello, " + sessionUser.getFirstName() + ". Welcome to the Admin Menu." + "\n"
				+ "\nPlease select from the below options:" + "\n"
				+ "\t1. View All User Accounts" + "\n"
				+ "\t2. View All User Bank Accounts" + "\n"
				+ "\t3. Edit User Accounts" + "\n"
				+ "\t4. Edit User Bank Accounts" + "\n"
				+ "\t5. Log Out"; 
		System.out.println(adminMenu);
		
		parseAdminMenuInput(collectInput());
		
	}
	
	public static void parseAdminMenuInput(int choice) {
		if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
			switch(choice) {
			
			case 1: {
				userDAO.getAllUsers();
				adminMenuScreen();
				break;
			}
			
			case 2: {
				baServ.viewAllBankAccounts(sessionUser.getUserId());
				adminMenuScreen();
				break;
			}
			
			case 3: {
				User targetUser = displayAllUserAccounts();
				System.out.println("You've selected to view user: " + targetUser.getUsername());
				System.out.println("\nWhat would you like to do?");
				displayEditUserOptions(targetUser);
				break;
			}
			
			case 4: {
				BankAccount targetBankAccount = displayAllBankAccounts();
				System.out.println("You've selected to view Bank Account: " + targetBankAccount.getAccountNumber());
				System.out.println("\nWhat would you like to do?");
				displayEditBankAccountOptions(targetBankAccount);
				break;
			}
			
			case 5: {
				uServ.logout(sessionUser);
				welcomeScreen();
				break;
			}
			default: {
				//
			}
			}
		}
	}
	
	public static void createUserAccountScreen() {
		System.out.println("We're happy you've made the choice to bank with us.");
		System.out.println("To begin, please enter your first name: ");
		
		String firstName = scanner.next();
		
		System.out.println("Next, please enter your last name: ");
		
		String lastName = scanner.next();
		
		System.out.println("\nThank you, " + firstName + "!");
		System.out.println("Now, please choose a username: ");
		
		String username = scanner.next();
		
		System.out.println("Your username '" + username + "' has been created.");
		System.out.println("Please enter a password that you'll use to access your account: ");
		
		String password = scanner.next();
		
		System.out.println("For accuracy and security, please re-enter your password:");
		
		String passwordCheck = scanner.next();
		
		// this while loop doesn't really work?
		while (!(password.equals(passwordCheck))) {
			System.out.println("Sorry, your passwords didn't match, please try again.");
			System.out.println("Please enter a password: ");
			
			password = scanner.next();
			
			System.out.println("For accuracy and security, please re-enter your password:");
			
			passwordCheck = scanner.next();
		}
		
		User user = new User(firstName, lastName, username, password);
		userDAO.createUser(user);
		sessionUser = user;
		System.out.println("Congratulations, you now have a user account!");
		
		// Navigate to Login Screen
		loginScreen();

	}
	
	public static void transactionScreen() {
		String transactionScreenMessage = "Below is a list of your Bank Accounts. To continue, please select an account.";			
		System.out.println(transactionScreenMessage);
		
		
		BankAccount account = displayAccounts();
		
		
		System.out.println("You've selected Account Number: " + account.getAccountNumber() + "\n"
							+ "\n Please select a transaction type." + "\n"
							+ "1. Make a withdrawal" + "\n"
							+ "2. Make a deposit" + "\n"
							+ "3. Close account" + "\n"
							+ "4. Return to Main Menu");
		
		
		switch (collectInput()) {
		
		case 1: {
			withdrawalScreen(account);
			break;
		}
		
		case 2: {
			depositScreen(account);
			break;
		}
		
		case 3: {
			closeAccountScreen(account);
			break;
		}
		
		case 4: {
			mainMenuScreen();
			break;
		}
		
		default: {
			//
		}
			
		}
										
	}

	public static void withdrawalScreen(BankAccount account) {
		
		
		System.out.println("How much would you like to withdraw from Account Number: " + account.getAccountNumber() + "?");
		
		double amount = collectInput();
		
		baServ.withdraw(account, amount);
		
		System.out.println("Transaction successful.");
		
		mainMenuScreen();
		
	}
	
	public static void depositScreen(BankAccount account) {
		
		System.out.println("How much would you like to deposit to Account Number: " + account.getAccountNumber());
		
		double amount = collectInput();
		
		baServ.deposit(account, amount);
		
		System.out.println("Transaction Successfull.");
		
		mainMenuScreen();
	}
	
	public static void closeAccountScreen(BankAccount account) {

		
		boolean success = baServ.closeAccount(account);
		
		if (success) {
			System.out.println("Your account has been closed.");
			mainMenuScreen();
		} else {
			mainMenuScreen();
		}
		
	}
	
	public static BankAccount displayAccounts() {
		List<BankAccount> accountList = baDAO.getAllUserBankAccountsById(sessionUser.getUserId());
		BankAccount targetAccount = null;
		
		if (accountList.size() == 0) {
			System.out.println("It seems you don't have any bank accounts with us yet. Please open a bank account to continue.");
			mainMenuScreen();
		}
		
		if (accountList.size() > 1) {
			System.out.println("Which account would you like to select?");
			for (BankAccount acct : accountList) {
				System.out.println((accountList.indexOf(acct) + 1) + ". " + acct.getAccountNumber());
			}
			
			int choice = collectInput();
			targetAccount = accountList.get(choice-1);
			return targetAccount;
			
		} else {
			System.out.println("You have only one account with us.");
			targetAccount = accountList.get(0);
			System.out.println("1. " + targetAccount.getAccountNumber());
			return targetAccount;
		}
	}
	
	public static User displayAllUserAccounts() { //superuser
		
		List<User> allUsers = userDAO.getAllUsers();
		User targetUser = null;
		
		System.out.println("Which user account would you like to view?");
		for (User u : allUsers) {
			System.out.println((allUsers.indexOf(u) + 1) + ". " + " User ID=" + u.getUserId() + " " + u.getFirstName() + " " + u.getLastName());
		}
		System.out.println("\n0. Create a new user");
		
		int choice = collectInput();
		
		if (choice == 0) {
			createUserAccountScreen();
		}
		targetUser = allUsers.get(choice - 1);
		
		return targetUser;
		
	}
	
	public static BankAccount displayAllBankAccounts() {
		List<BankAccount> allBankAccounts = baServ.viewAllBankAccounts(sessionUser.getUserId());
		BankAccount targetBankAccount = null;
		
		System.out.println("Which user bank account would you like to view?");
		
		for (BankAccount a : allBankAccounts) {
			System.out.println((allBankAccounts.indexOf(a) + 1) + ". " + a.getAccountNumber() + " " + a.getAccountBalance());
		}
		int choice = collectInput();
		
		targetBankAccount = allBankAccounts.get(choice -1);
		
		return targetBankAccount;
	}
	
	public static void displayEditUserOptions(User targetUser) {
		String options = "\n\t1. Edit Name, username, or password" + "\n"
						+ "\t2. View transaction history" + "\n"
						+ "\t3. Delete User Account";
		System.out.println(options);
		
		switch(collectInput()) {
		
		case 1: {
			editUser(targetUser);
			adminMenuScreen();
			break;
		}
		
		case 2: {
			//view transaction history
			adminMenuScreen();
			break;
		}
		
		case 3: {
			System.out.println("Are you sure you want to delete this account? \nY/N");
			
			String choice = scanner.next();
			if (choice == "Y") {
				userDAO.deleteUser(targetUser);
			}
			adminMenuScreen();
			break;
		}
		
		default: {
			System.out.println("It seems you've selected an option that doesn't exist.");
			adminMenuScreen();
			break;
		}
		}
	}
	
	public static User editUser(User targetUser) {
		
		System.out.println("You've selected to edit user: " + targetUser.getUsername());
		System.out.println("What would you like to edit?");
		System.out.println("\n\t1. Edit First or Last Name" + "\n"
							+ "\t2. Edit User's username" + "\n"
							+ "\t3. Edit User's password");
		
		switch(collectInput()) {
		
		case 1: {
			System.out.println("Existing name = " + targetUser.getFirstName() + " " + targetUser.getLastName());
			System.out.println("Please enter new first name:");
			String firstName = scanner.next();
			System.out.println("Please enter new last name:");
			String lastName = scanner.next();
			
			targetUser.setFirstName(firstName);
			targetUser.setLastName(lastName);
			
			userDAO.updateUser(targetUser);
			
			System.out.println("You've successfully edited User ID " + targetUser.getUserId() + "'s name to be: " + firstName + " " + lastName);
			adminMenuScreen();
			break;
			
		}
		
		case 2: {
			System.out.println("Current User's username = " + targetUser.getUsername());
			System.out.println("Please enter new username:");
			String username = scanner.next();
			
			targetUser.setUsername(username);
			
			userDAO.updateUser(targetUser);
			System.out.println("You've successfully edited " + targetUser.getFirstName() + "'s username to be: " + username);
			adminMenuScreen();
			break;
		}
		
		case 3: {
			System.out.println("Current User's password = " + targetUser.getPassword());
			System.out.println("Please type a new password: ");
			String password = scanner.next();
			System.out.println("Please re-enter password: ");
			String passwordCheck = scanner.next();
			
			while (!(password.equals(passwordCheck))) {
				System.out.println("Sorry, those passwords didn't match, please try again.");
				System.out.println("Please enter a password: ");
				password = scanner.next();
				System.out.println("For accuracy and security, please re-enter your password:");
				passwordCheck = scanner.next();
			}
			
			targetUser.setPassword(passwordCheck);
			userDAO.updateUser(targetUser);
			
			System.out.println("You've successfully updated " + targetUser.getUsername() + "'s password to be: " + passwordCheck);
			
			adminMenuScreen();
			break;
		}
		
		default: {
			adminMenuScreen();
			break;
		}
		}
		
		return targetUser;
	}
	
	public static void displayEditBankAccountOptions(BankAccount targetBankAccount) {
		
		String options = "\n\t1. View Transaction History" + "\n"
						+ "\t2. Edit Account Balance" + "\n"
						+ "\t3. Close Account";
		
		System.out.println(options);
		
		switch(collectInput()) {
		
		case 1: {
			//View Transaction History
			adminMenuScreen();
			break;
		}
		
		case 2: {
			editAccount(targetBankAccount);
			adminMenuScreen();
			break;
		}
		}
	}
	
	public static BankAccount editAccount(BankAccount targetBankAccount) {
		System.out.println("You've selected to edit the balance of Bank Account: " + targetBankAccount.getAccountNumber());
		System.out.println("What would you like to set the balance to?");
		
		double newBalance = collectInput();
		
		targetBankAccount.setAccountBalance(newBalance);
		
		baDAO.updateAccount(targetBankAccount);
		
		System.out.println("You have successfully updated the balance of Account Number " + targetBankAccount.getAccountNumber() + " to be: " + newBalance);
		
		adminMenuScreen();
		
		return targetBankAccount;
	}
	
	
}
