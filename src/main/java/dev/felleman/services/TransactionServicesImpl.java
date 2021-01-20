package dev.felleman.services;

import java.util.List;

import dev.felleman.daos.TransactionDAO;
import dev.felleman.daos.TransactionDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.Transaction;
import dev.felleman.entities.User;

public class TransactionServicesImpl implements TransactionServices {
	
	TransactionDAO tDAO = new TransactionDAOImpl();

	@Override
	public List<Transaction> viewAllTransactions() {
		List<Transaction> allTransactions = tDAO.getAllTransactions();
		return allTransactions;
	}

	@Override
	public List<Transaction> viewAllUserTransactions(User user) {
		List<Transaction> allUserTransactions = tDAO.getAllUserTransactions(user);
		return allUserTransactions;
	}

	@Override
	public List<Transaction> viewAllBankAccountTransactions(BankAccount account) {
		List<Transaction> allBankAccountTransactions = tDAO.getAllBankAccountTransactions(account);
		return allBankAccountTransactions;
	}

}
