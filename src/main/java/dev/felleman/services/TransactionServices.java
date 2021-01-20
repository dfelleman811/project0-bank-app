package dev.felleman.services;

import java.util.List;

import dev.felleman.entities.BankAccount;
import dev.felleman.entities.Transaction;
import dev.felleman.entities.User;

public interface TransactionServices {
	
	public List<Transaction> viewAllTransactions();
	
	public List<Transaction> viewAllUserTransactions(User user);
	
	public List<Transaction> viewAllBankAccountTransactions(BankAccount account);

}
