package dev.felleman.daotests;

import java.sql.Connection;
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
import dev.felleman.util.JDBCConnection;

class BankAccountDaoTests {
	
	BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	UserDAO userDAO = new UserDAOImpl();
	Connection conn = JDBCConnection.getConnection();

	@Test
	void openAccountTest() {
//		BankAccount acct = new BankAccount();
//		acct.setUserId(4);
//		acct.setAccountNumber(100002);
		User user = userDAO.getUserById(8);
		bankAccountDAO.openAccount(user);
		
		User user6 = userDAO.getUserById(6);
		bankAccountDAO.openAccount(user6);
		
		User user7 = userDAO.getUserById(7);
		bankAccountDAO.openAccount(user7);
		
		//Assertions.assertEquals(acct, bankAccountDAO.getBankAccountById(user.getUserId()));
		
	}
	
	
	@Test
	void getBalanceTest() {
		BankAccount acct = new BankAccount();
		acct.setUserId(4);
		acct.setAccountNumber(100002);
		
		Assertions.assertEquals(acct, bankAccountDAO.getBalance(acct));
		
	}
	
//	@Test // test in services
//	void depositTest() {
//		BankAccount acct = new BankAccount();
//		acct.setUserId(4);
//		acct.setAccountNumber(100002);
//		
//		
//		Assertions.assertEquals(1500, account.getAccountBalance());
//	}
//	
//	@Test
//	void withdrawTest() {
//		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
//		
//		BankAccount account = bankAccountDAO.openAccount(user);
//		account.setAccountBalance(500);
//		
//		bankAccountDAO.withdraw(account, 250);
//		
//		Assertions.assertEquals(250, account.getAccountBalance());
//	}

	@Test
	void getBankAccountByAccountNumberTest() {
	
		BankAccount acct = new BankAccount();
		acct.setUserId(4);
		acct.setAccountNumber(100002);
		
		Assertions.assertEquals(acct, bankAccountDAO.getBankAccountByAccountNumber(100002));
		
		
	}
	
	@Test
	void getBankAccountByIdTest() {
		BankAccount acct = new BankAccount();
		acct.setUserId(4);
		acct.setAccountNumber(100002);
		
		Assertions.assertEquals(acct, bankAccountDAO.getBankAccountById(4));
		
	}
	
	
	@Test
	void getAllUserBankAccountsByIdTest() {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		BankAccount ba1 = bankAccountDAO.getBankAccountByAccountNumber(100002);
		BankAccount ba2 = bankAccountDAO.getBankAccountByAccountNumber(100003);
		bankAccounts.add(ba1);
		bankAccounts.add(ba2);
		
		Assertions.assertEquals(bankAccounts, bankAccountDAO.getAllUserBankAccountsById(4));
		
	}
	
	@Test
	void getAllBankAccountsTest() {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		BankAccount ba1 = bankAccountDAO.getBankAccountByAccountNumber(100001);
		
		BankAccount ba2 = bankAccountDAO.getBankAccountByAccountNumber(100002);
		
		BankAccount ba3 = bankAccountDAO.getBankAccountByAccountNumber(100003);
		
		bankAccounts.add(ba1);
		bankAccounts.add(ba2);
		bankAccounts.add(ba3);
		
		Assertions.assertEquals(bankAccounts, bankAccountDAO.getAllBankAccounts());
	}
	
	@Test
	void updateAccountTest() {
		
		BankAccount ba = bankAccountDAO.getBankAccountByAccountNumber(100001);
		
		ba.setAccountBalance(500);
		
		bankAccountDAO.updateAccount(ba);
		
		Assertions.assertEquals(500, bankAccountDAO.getBalance(ba).getAccountBalance());
		
	}
	
	@Test
	void closeAccountTest() {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		//BankAccount ba1 = bankAccountDAO.getBankAccountByAccountNumber(100001);
		//BankAccount ba4 = bankAccountDAO.getBankAccountByAccountNumber(100003);
		//BankAccount ba5 = bankAccountDAO.getBankAccountByAccountNumber(100004);
		//BankAccount ba8 = bankAccountDAO.getBankAccountByAccountNumber(100005);
		BankAccount ba6 = bankAccountDAO.getBankAccountByAccountNumber(100006);
		BankAccount ba7 = bankAccountDAO.getBankAccountByAccountNumber(100007);
		
		
		//bankAccounts.add(ba1);
		//bankAccounts.add(ba4);
		//bankAccounts.add(ba5);
		//bankAccounts.add(ba8);
		bankAccounts.add(ba6);
		bankAccounts.add(ba7);
		
		
		BankAccount acct = bankAccountDAO.getBankAccountByAccountNumber(100005);
		bankAccountDAO.closeAccount(acct);
		
		
		Assertions.assertEquals(bankAccounts, bankAccountDAO.getAllBankAccounts());
		
		
		
	}
		
}




















