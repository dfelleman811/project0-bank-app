package dev.felleman.daotests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.felleman.daos.TransactionDAO;
import dev.felleman.daos.TransactionDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.Transaction;
import dev.felleman.entities.User;

class TransactionDAOTests {
	
	TransactionDAO tDAO = new TransactionDAOImpl();

	@Test
	void createTransactionTest() {
		
		//Transaction
		
	}
	
	@Test
	void getTransactionTest() {
		Transaction t = new Transaction();
		
		t.setAccountNumber(100024);
		t.setTransactionType("withdrawal");
		//t.setDatetime(Date.valueOf(LocalDate.now())); 
		t.setTransactionAmount(800);
		t.setAccountBalance(0);
		t.setUserId(22);
		t.setTransactionId(200053);
		
		Assertions.assertEquals(t, tDAO.getTransaction(200053));
	}
	
	@Test
	void getAllTransactionsTest() {
		
		List<Transaction> transactions = tDAO.getAllTransactions();
		
		
		System.out.println(transactions);
		
		
	}

	
	@Test
	void getAllUserTransactionsTest() {
		
		User u = new User();
		u.setUserId(6);
		
		List<Transaction> allUserTransactions = tDAO.getAllUserTransactions(u);
		System.out.println(allUserTransactions);
	}
	
	@Test
	void getAllBankAccountTransactionsTest() {
		
		BankAccount a = new BankAccount();
		a.setAccountNumber(100006);
		
		List<Transaction> allActTrans = tDAO.getAllBankAccountTransactions(a);
		
		System.out.println(allActTrans);
	}

}
