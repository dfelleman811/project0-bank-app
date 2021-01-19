package dev.felleman.services;

import java.util.List;

import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;

public interface BankAccountServices {
	
	public boolean openAccount(User user);
	
	public BankAccount viewBalance(BankAccount account);
	
	public List<BankAccount> viewAllUserBankAccounts(int userId);
	
	public List<BankAccount> viewAllBankAccounts(int userId); // for superuser
	
	public BankAccount deposit(BankAccount account, double amount);
	
	public BankAccount withdraw(BankAccount account, double amount);
	
	public boolean closeAccount(BankAccount account);

}
