package dev.felleman.daos;

import java.util.List;

import dev.felleman.entities.BankAccount;
import dev.felleman.entities.Transaction;
import dev.felleman.entities.User;

public interface TransactionDAO {
	
	
	// CREATE
	public boolean createTransaction(Transaction transaction, BankAccount account);

	
	
	// READ
	public Transaction getTransaction(int transactionId);
	
	public List<Transaction> getAllTransactions();
	
	public List<Transaction> getAllUserTransactions(User user);
	
	public List<Transaction> getAllBankAccountTransactions(BankAccount bankAccount);
	
	public boolean updateTransaction(Transaction transaction);
	
	
	// UPDATE ....do I really need to update Transactions?...I don't think so....
	

	// DELETE .....I also don't ever want to delete them...I'm starting to doubt that I need this class...
	
	
}
