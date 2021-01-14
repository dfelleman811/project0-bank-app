package dev.felleman.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.felleman.daos.BankAccountDAO;
import dev.felleman.daos.BankAccountDAOImpl;
import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;
import dev.felleman.services.UserServices;
import dev.felleman.services.UserServicesImpl;

class BankAccountDaoTests {
	
	UserDAO uDAO = new UserDAOImpl();
	BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	UserServices uServ = new UserServicesImpl();

	@Test
	void openAccountTest() {
		
		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
		
		BankAccount act = bankAccountDAO.openAccount(user);
		
		User user2 = uDAO.createUser("Matthew", "Felleman", "mfelleman", "alsopassword");
		
		BankAccount act2 = bankAccountDAO.openAccount(user2);
		
		Assertions.assertEquals(act, bankAccountDAO.getBankAccountById(user.getUserId()));
		Assertions.assertEquals(act2, bankAccountDAO.getBankAccountById(user2.getUserId()));
		
	}
	
	
	@Test
	void viewBalanceTest() {
		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
		BankAccount account = bankAccountDAO.openAccount(user);
		bankAccountDAO.deposit(account, 500);
		BankAccount account2 = bankAccountDAO.openAccount(user);
		bankAccountDAO.deposit(account2, 2000);
		
		User user2 = uDAO.createUser("George", "Justin", "gmanj", "password");
		BankAccount account3 = bankAccountDAO.openAccount(user2);
		bankAccountDAO.deposit(account3, 5000);
		BankAccount account4 = bankAccountDAO.openAccount(user2);
		bankAccountDAO.deposit(account4, 1500);
		
		
		
		bankAccountDAO.viewBalance(user2);
		
	}
	
	@Test
	void depositTest() {
		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
		BankAccount account = bankAccountDAO.openAccount(user);
		account.setAccountBalance(500);
		
		bankAccountDAO.deposit(account, 1000);
		
		Assertions.assertEquals(1500, account.getAccountBalance());
	}
	
	@Test
	void withdrawTest() {
		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
		
		BankAccount account = bankAccountDAO.openAccount(user);
		account.setAccountBalance(500);
		
		bankAccountDAO.withdraw(account, 250);
		
		Assertions.assertEquals(250, account.getAccountBalance());
	}

	@Test
	void getAllUserBankAccountsByIdTest() {
		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
		BankAccount account = bankAccountDAO.openAccount(user);
		BankAccount account2 = bankAccountDAO.openAccount(user);
		
		User user2 = uDAO.createUser("George", "Justin", "gmanj", "password");
		BankAccount account3 = bankAccountDAO.openAccount(user2);
		BankAccount account4 = bankAccountDAO.openAccount(user2);
		
		System.out.println(bankAccountDAO.getAllUserBankAccountsById(user.getUserId()));
		
		//System.out.println(bankAccountDAO.getAllUserBankAccountsById(user.getUserId()));
		
//		Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
//		
//		bankAccounts.add(account);
//		bankAccounts.add(account2);
//		bankAccounts.add(account3);
//		bankAccounts.add(account4);
//		
//		for (BankAccount act : bankAccounts) {
//			if (act.getUserId() == user.getUserId()) {
//				System.out.println(act);
//			}
//		}
		//System.out.println(bankAccounts);
	}
		@Test
		void getBankAccountByIdTest() {
			
		}
	}

