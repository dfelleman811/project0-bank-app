package dev.felleman.servicestests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.felleman.daos.BankAccountDAO;
import dev.felleman.daos.BankAccountDAOImpl;
import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;
import dev.felleman.services.BankAccountServices;
import dev.felleman.services.BankAccountServicesImpl;

class BankAccountServicesTests {

	BankAccountServices baServ = new BankAccountServicesImpl();
	
	UserDAO userDAO = new UserDAOImpl();
	
	BankAccountDAO baDAO = new BankAccountDAOImpl();
	
	
	
	@Test
	void openAccountTest() {
		User user = userDAO.getUserById(10);
		
		baServ.openAccount(user);
		
		Assertions.assertEquals(100010, baDAO.getBankAccountById(user.getUserId()).getAccountNumber());
		
	}
	
	@Test
	void viewBalanceTest() {
		
		User user = userDAO.getUserById(10);
		
		BankAccount ba = baDAO.getBankAccountById(user.getUserId());
		
		double balance = baServ.viewBalance(ba).getAccountBalance();
		
		Assertions.assertEquals(0, balance);
		
	}
	
	
	@Test
	void viewAllUserBankAccountsTest() {
		User user = userDAO.getUserById(7);
		
		BankAccount ba1 = new BankAccount();
		ba1.setAccountNumber(100007);
		ba1.setUserId(7);
		
		BankAccount ba2 = new BankAccount();
		ba2.setAccountNumber(100009);
		ba2.setUserId(7);
		
		List<BankAccount> baList = new ArrayList<BankAccount>();
		baList.add(ba1);
		baList.add(ba2);
				
				
		Assertions.assertEquals(baList, baServ.viewAllUserBankAccounts(user.getUserId()));	
		
	}
	
	@Test
	void depositTest() {
		
		BankAccount acct = baServ.deposit(baDAO.getBankAccountById(7), 1000);
		
		
		Assertions.assertEquals(2050, acct.getAccountBalance());
	}
	
	@Test
	void withdrawTest() {
		
		BankAccount acct = baServ.withdraw(baDAO.getBankAccountById(22), 800);
		
		Assertions.assertEquals(75, acct.getAccountBalance());
		
	}
	
	@Test
	void closeAccountTest() {
		
		boolean success = baServ.closeAccount(baDAO.getBankAccountByAccountNumber(100007));
		
		Assertions.assertEquals(true, success);;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
