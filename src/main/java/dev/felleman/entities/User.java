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
	
	private int registered;
	private int loggedIn;
	
//	private int accountNumber;
//	private double accountBalance;
	//private int savingsAccountNumber;
	
	private int isSuper;

	
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
		this.registered = 0;
		this.loggedIn = 0;
//		this.accountNumber = 0;
//		this.accountBalance = 0;
		this.isSuper = 0;
		//this.savingsAccountNumber = 0;
	}

	
	public User(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.userId = 0;
		this.registered = 0;
		this.loggedIn = 0;
//		this.accountNumber = checkingAccountNumber;
//		this.accountBalance = accountBalance;
		this.isSuper = 0;
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

	public int getRegistered() {
		return this.registered;
	}

	public void setRegistered(int registered) {
		this.registered = registered;
	}
	
	public int getLoggedIn() {
		return this.loggedIn;
	}
	
	public void setLoggedIn(int loggedIn) {
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
	
	public int getIsSuper() {
		return this.isSuper;
	}
	
	public void setIsSuper(int isSuper) {
		this.isSuper = isSuper;
	}
	
	
	// Override toString()
	@Override
	public String toString() {
		return "User [userId=" + this.userId + ", firstName=" + this.firstName + ", lastName=" + this.lastName 
				+ ", username=" + this.username + ", password=" + this.password + ", registered=" + this.registered 
				+ ", loggedIn=" + this.loggedIn + ", isSuper=" + this.isSuper + "]";
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isSuper != other.isSuper)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (loggedIn != other.loggedIn)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registered != other.registered)
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}
