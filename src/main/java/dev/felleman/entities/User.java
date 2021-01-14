package dev.felleman.entities;

/**
 * 
 * This class represents a User. 
 * 
 * TODO do I want to create a separate entity for accounts? Or can account information be stored here as well? 
 *
 * @author DanielFelleman
 *
 */
public class User {
	
	// Instance Variables
	private int userId; // this acts as the primary key
	private String firstName;
	private String lastName;
	
	private String username;
	private String password;
	
	private boolean registered;
	private boolean loggedIn;
	
//	private int accountNumber;
//	private double accountBalance;
	//private int savingsAccountNumber;
	
	private boolean isSuper;

	
	// Constructors
	public User() {
		super();
	}

	public User(String firstName, String lastName, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = null;
		this.userId = 0;
		this.registered = false;
		this.loggedIn = false;
//		this.accountNumber = 0;
//		this.accountBalance = 0;
		this.isSuper = false;
		//this.savingsAccountNumber = 0;
	}

	
	public User(String firstName, String lastName, String username, String password, int userId, boolean registered) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userId = userId;
		this.registered = registered;
		this.loggedIn = false;
//		this.accountNumber = checkingAccountNumber;
//		this.accountBalance = accountBalance;
		this.isSuper = false;
		//this.savingsAccountNumber = savingsAccountNumber;
	}

	// Getters and Setters
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isRegistered() {
		return this.registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
	public boolean getLoggedIn() {
		return this.loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

//	public int getAccountNumber() {
//		return this.accountNumber;
//	}

//	public void setCheckingAccountNumber(int checkingAccountNumber) {
//		this.checkingAccountNumber = checkingAccountNumber;
//	}
//
//	public int getSavingsAccountNumber() {
//		return this.savingsAccountNumber;
//	}

//	public void setAccountNumber(int savingsAccountNumber) {
//		this.accountNumber = savingsAccountNumber;
//	}
//	
//	public double getAccountBalance() {
//		return this.accountBalance;
//	}
//	
//	public void setAccountBalance(double accountBalance) {
//		this.accountBalance = accountBalance;
//	}
	
	public boolean getIsSuper() {
		return this.isSuper;
	}
	
	public void setIsSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}
	
	
	// Override toString()
	@Override
	public String toString() {
		return "User [userId=" + this.userId + ", firstName=" + this.firstName + ", lastName=" + this.lastName 
				+ ", username=" + this.username + ", password=" + this.password + ", registered=" + this.registered 
				+ ", loggedIn=" + this.loggedIn + ", isSuper=" + this.isSuper + "]";
	}
	
	
	
}
