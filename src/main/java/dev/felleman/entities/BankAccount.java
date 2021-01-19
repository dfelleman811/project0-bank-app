package dev.felleman.entities;

public class BankAccount {
	
	//Instance Variables
	int userId; 
	int accountNumber;	
	//String accountType;
	double accountBalance;
	
	//double interestRate;
	
	// Constructors
	public BankAccount() {
		super();
		//this.accountType = "Savings";
	}
	
	public BankAccount(int userId) {
		this.userId = userId;
		this.accountNumber = 0;
		this.accountBalance = 0;
		//this.accountType = "Savings";
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
	
//	public String getAccountType() {
//		return this.accountType;
//	}
	
//	public void setAccountType(String accountType) {
//		this.accountType = accountType;
//	}

	@Override
	public String toString() {
		return "BankAccount [userId=" + this.userId + ", accountNumber=" + this.accountNumber + ", accountBalance="
				+ this.accountBalance + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
