package dev.felleman.app;

import java.util.Scanner;

import dev.felleman.daos.BankAccountDAO;
import dev.felleman.daos.BankAccountDAOImpl;
import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;
import dev.felleman.services.UserServices;
import dev.felleman.services.UserServicesImpl;

/**
 * The BankApp class will take the place of a user interface for this project 0. 
 * 
 * The main logic and flow control will be placed inside a main method in this class. 
 * 
 * We will need a scanner - to get user input
 * @author DanielFelleman
 *
 */
public class BankApp {
	
	private static Scanner scanner = new Scanner(System.in);
	
	// Instantitate a DAO to track all changes
	private static UserDAO userDAO = new UserDAOImpl();
	
	// And a services impl for interacting with the accounts?
	private static UserServices uServ = new UserServicesImpl();
	
	// BankAccountDAO
	private static BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	
	private static User sessionUser;
	
	
	public static void main(String[] args ) {
		
		// TODO This will be the main logic for user interaction with the map
		
		// basic user used for testing
		User testUser = userDAO.createUser("dan", "felleman", "dfelleman", "password");
		System.out.println(testUser);
		// Welcome message
		printWelcomeMessage();
		
		// Collect Input
		int choice = collectInput();
		
		// Parse Input
		parseWelcomeInput(choice);
		
		// Menu
		
		printMainMenu();
		
		
		
		int selection = scanner.nextInt();
		
		parseMenuInput(selection);
		
		
		// Transaction Menu
		String transactionMenu = "Please select from the following transactions:" + "\n"
								+ "1. Make a withdrawal" + "\n"
								+ "2. Make a deposit" + "\n"
								+ "3. Close Account"; 
		
		//int choice = scanner.nextInt();
		//what amount? 
		// confirm complete
		// Transaction Summary
		// back
		
		// Start all over?
		
	}
	
	// Methods
	
	public static void printWelcomeMessage() {
		String welcomeMessage = "\nWelcome to the Banking App!" + "\n"
				+ "Please choose an option to continue:" + "\n"
				+ "1. Log In - for registered users." + "\n"
				+ "2. Register New User";

		System.out.println(welcomeMessage);
	}
	
	public static int collectInput() {
		int choice = scanner.nextInt();
		return choice;
	}
	
	public static void parseWelcomeInput(int choice) {
		
		// make sure they've entered a valid option
		if (choice == 1 || choice == 2) { 						// TODO switch w/default OR if/else
			switch(choice) {
			
			case 1: {
				// Ask for username and password
				System.out.println("Please enter your username: ");
				String username = scanner.next();
				System.out.println("Please enter your password: ");
				String password = scanner.next();
				
				User user = userDAO.getUserByUsername(username);
				uServ.login(user, username, password);
				sessionUser = user;
				System.out.println("Hello, " + user.getFirstName() + ". Welcome to your home page.");
				break;
			}
			
			case 2: {
				System.out.println("We're happy you've made the choice to bank with us.");
				System.out.println("To begin, please enter your first name: ");
				
				String firstName = scanner.next();
				
				System.out.println("Next, please enter your last name: ");
				
				String lastName = scanner.next();
				
				System.out.println("\nThank you, " + firstName + "!");
				System.out.println("Now, please choose a username: ");
				
				String username = scanner.next();
				
				System.out.println("Your username " + username + " has been created.");
				System.out.println("Please enter a password that you'll use to access your account: ");
				
				String password = scanner.next();
				
				System.out.println("For accuracy and security, please re-enter your password:");
				
				String passwordCheck = scanner.next();
				
				// this while loop doesn't really work
				while (password != passwordCheck) {
					System.out.println("Sorry, your passwords didn't match, please try again.");
					System.out.println("Please enter a password: ");
					
					password = scanner.next();
					
					System.out.println("For accuracy and security, please re-enter your password:");
					
					passwordCheck = scanner.next();
				}
				
				User user = userDAO.createUser(firstName, lastName, username, password);
				sessionUser = user;
				System.out.println("Congratulations, you now have a user account.");
		
					
			}
			
			default: {
				// default implementation if input doesn't match any choices
				System.out.println("WRONG PLEASE TRY AGAIN");
			}
				
			}
		}
		
	}

	public static void printMainMenu() {
		String mainMenu = "You are logged in as: [INSERT USERNAME]" + "\n"
				+ "Please select from the below options:" + "\n"
				+ "1. View Bank Accounts and Balances" + "\n"
				+ "2. Open Bank Account" + "\n"
				+ "3. Complete a Transaction" + "\n"
				+ "4. Log Out";// TODO 
		System.out.println(mainMenu);
	}
	
	public static void parseMenuInput(int choice) {

		// make sure they've entered a valid input
			if (choice == 1 || choice == 2 || choice == 3) {
				switch (choice) {
				
					// view bank accounts
					case 1: {
						BankAccount account = bankAccountDAO.getBankAccountById(sessionUser.getUserId());
						System.out.println(account);
						break;
					}
					
					// create a bank account
					case 2: {
						BankAccount account = bankAccountDAO.openAccount(sessionUser);
						System.out.println("Congratulations. You now have a bank account with us.");
						System.out.println(account);
		
						break;
					}
					
					// make a transaction
					case 3: {
						
						//uServ.deposit(sessionUser,)
						// if user has more than one account
						//if (bankAccountDAO.getBankAccountById(sessionUser.getUserId()).size() )
						
						break;
					}
					
//					default: {
//						
//						
//					}
				}
		
			}
			
			if (choice == 4) {
				uServ.logout(sessionUser);
				printWelcomeMessage();
			}
			
			
	}
	
}
