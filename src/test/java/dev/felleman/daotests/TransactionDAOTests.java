package dev.felleman.daotests;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.felleman.daos.TransactionDAO;
import dev.felleman.daos.TransactionDAOImpl;
import dev.felleman.entities.Transaction;

class TransactionDAOTests {
	
	TransactionDAO tDAO = new TransactionDAOImpl();

	@Test
	void createTransactionTest() {
		
		Transaction t = new Transaction();
		
		t.setAccountNumber(100006);
		t.setDatetime(Date.from(Instant.now()));
		t.setTransactionType("deposit");
		t.setTransactionAmount(500);
		t.setAccountBalance(1500);
		t.setUserId(6);
		
		tDAO.createTransaction(t);
		
		System.out.println(t);
		
//		Assertions.assertEquals(null, null);
		
	}
	
	@Test
	void getTransactionTest() {
		Transaction t = new Transaction();
		
		t.setAccountNumber(100021);
		t.setTransactionType("deposit");
		//t.setDatetime(Date.valueOf(LocalDate.now()));
		t.setTransactionAmount(500);
		t.setAccountBalance(1500);
		t.setUserId(21);
		t.setTransactionId(200041);
		
		Assertions.assertEquals(t, tDAO.getTransaction(200041));
	}
	


}
