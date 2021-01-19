package dev.felleman.services;

import java.util.List;

import dev.felleman.daos.BankAccountDAO;
import dev.felleman.daos.BankAccountDAOImpl;
import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;

public class BankAccountServicesImpl implements BankAccountServices {

	public BankAccountDAO baDAO = new BankAccountDAOImpl();
	public UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public boolean openAccount(User user) {
		boolean success = baDAO.openAccount(user);
		return success;
	}

	@Override
	public BankAccount viewBalance(BankAccount account) {
		BankAccount acct = baDAO.getBalance(account);
		return acct;
	}

	@Override
	public List<BankAccount> viewAllUserBankAccounts(int userId) {
		List<BankAccount> userBankAccounts = baDAO.getAllUserBankAccountsById(userId);
		System.out.println("\t******* YOUR ACCOUNTs *******");
		System.out.println("ACCOUNT NUMBER ....................... BALANCE");
		for (BankAccount acct : userBankAccounts) {
			System.out.println(acct.getAccountNumber() + "......................................" + acct.getAccountBalance());
			
		}
		return userBankAccounts;
	}
	
	@Override
	public List<BankAccount> viewAllBankAccounts(int userId) {
		
		if (userDAO.getUserById(userId).getIsSuper() == 1) {
			List<BankAccount> allBankAccounts = baDAO.getAllBankAccounts();
			return allBankAccounts;
		} else {
			return null;
		}
		
	}

	@Override
	public BankAccount deposit(BankAccount account, double amount) {
		BankAccount acct = baDAO.getBankAccountByAccountNumber(account.getAccountNumber());
		acct.setAccountBalance(account.getAccountBalance() + amount);
		baDAO.updateAccount(acct);
		return acct;
	}

	@Override
	public BankAccount withdraw(BankAccount account, double amount) {
		BankAccount acct = baDAO.getBankAccountByAccountNumber(account.getAccountNumber());
		acct.setAccountBalance(account.getAccountBalance() - amount);
		baDAO.updateAccount(acct);
		return acct;
	}

	@Override
	public boolean closeAccount(BankAccount account) {
		
		BankAccount acct = baDAO.getBankAccountByAccountNumber(account.getAccountNumber());
		
		// check to make sure account is empty
		if (acct.getAccountBalance() != 0) {
			System.out.println("Your account must have a 0 balance in order to close.");
		} else {
			baDAO.closeAccount(acct);
			return true;
		}
	
		return false;
	}

}
