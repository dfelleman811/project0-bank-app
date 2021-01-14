package dev.felleman.daos;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;

public interface BankAccountDAO {
	
	// CREATE
	public BankAccount openAccount(User user); // do I want these to return a User? 
	
	// READ
	public void viewBalance(User user);
	
	public Collection<BankAccount> getBankAccountById(int userId);
	
	public Collection<BankAccount> getAllUserBankAccountsById(int userId);
	
	public List<BankAccount> getAllBankAccounts();
	
	// UPDATE
	public BankAccount withdraw(BankAccount account, double amount); // these should go in services... or are they technically updating an account?
	
	public BankAccount deposit(BankAccount account, double amount);

	
	public BankAccount updateAccount(BankAccount account);
	
	// DELETE
	public BankAccount closeAccount(BankAccount account);
}
