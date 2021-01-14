package dev.felleman.entities;

public class BankAccount {
	
	//String accountType;
	int userId; // hmmm how can I make this correlate to the User class? maybe when I register a user I can call openAccount
	int accountNumber;
	String accountType;
	
	double accountBalance;
	
	//double interestRate;
	
	public BankAccount() {
		super();
		this.accountType = "Savings";
	}
	
	public BankAccount(int userId, int accountNumber) {
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountBalance = 0;
		this.accountType = "Savings";
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public String getAccountType() {
		return this.accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "BankAccount [userId=" + this.userId + ", accountNumber=" + this.accountNumber + ", accountBalance="
				+ this.accountBalance + "]";
	}
	
	

}
